package org.project.service;

import org.project.entity.Filme;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CsvParseService {

    private static final char DEFAULT_SEPARATOR = ';';
    private static final char DOUBLE_QUOTES = '"';
    private static final char DEFAULT_QUOTE_CHAR = DOUBLE_QUOTES;
    private static final String NEW_LINE = "\n";

    private boolean isMultiLine = false;
    private String pendingField = "";
    private String[] pendingFieldLine = new String[]{};

    public List<Filme> csv() throws Exception {

        URL resource = CsvParseService.class.getClassLoader().getResource("files/movielist.csv");
        File file = Paths.get(resource.toURI()).toFile();

        CsvParseService obj = new CsvParseService();
        List<String[]> result = obj.readFile(file, 1);
        List<Filme> filmes = new ArrayList<>();

        int listIndex = 0;
        for (String[] arrays : result) {

            Filme filme = new Filme();

            int index = 0;
            for (String array : arrays) {

                switch (index) {
                    case 0:
                        filme.setYear(Integer.valueOf(array));
                        break;

                    case 1:
                        filme.setTitle(array);
                        break;

                    case 2:
                        filme.setStudios(array);
                        break;

                    case 3:
                        filme.setProducers(array);
                        break;

                    case 4:
                        filme.setWinner("yes".equalsIgnoreCase(array) ? true : false);
                        break;
                }
                index++;
            }

            filmes.add(filme);

        }

        return filmes;

    }

    public List<String[]> readFile(File csvFile) throws Exception {
        return readFile(csvFile, 0);
    }

    public List<String[]> readFile(File csvFile, int skipLine)
            throws Exception {

        List<String[]> result = new ArrayList<>();
        int indexLine = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line;
            while ((line = br.readLine()) != null) {

                if (indexLine++ <= skipLine) {
                    continue;
                }

                String[] csvLineInArray = parseLine(line);

                if (isMultiLine) {
                    pendingFieldLine = joinArrays(pendingFieldLine, csvLineInArray);
                } else {

                    if (pendingFieldLine != null && pendingFieldLine.length > 0) {
                        result.add(joinArrays(pendingFieldLine, csvLineInArray));
                        pendingFieldLine = new String[]{};
                    } else {
                        result.add(csvLineInArray);
                    }

                }


            }
        }

        return result;
    }

    public String[] parseLine(String line) throws Exception {
        return parseLine(line, DEFAULT_SEPARATOR);
    }

    public String[] parseLine(String line, char separator) throws Exception {
        return parse(line, separator, DEFAULT_QUOTE_CHAR).toArray(String[]::new);
    }

    private List<String> parse(String line, char separator, char quoteChar)
            throws Exception {

        List<String> result = new ArrayList<>();

        boolean inQuotes = false;
        boolean isFieldWithEmbeddedDoubleQuotes = false;

        StringBuilder field = new StringBuilder();

        for (char c : line.toCharArray()) {

            if (c == DOUBLE_QUOTES) {
                if (isFieldWithEmbeddedDoubleQuotes) {

                    if (field.length() > 0) {
                        field.append(DOUBLE_QUOTES);
                        isFieldWithEmbeddedDoubleQuotes = false;
                    }

                } else {
                    isFieldWithEmbeddedDoubleQuotes = true;
                }
            } else {
                isFieldWithEmbeddedDoubleQuotes = false;
            }

            if (isMultiLine) {
                field.append(pendingField).append(NEW_LINE);
                pendingField = "";
                inQuotes = true;
                isMultiLine = false;
            }

            if (c == quoteChar) {
                inQuotes = !inQuotes;
            } else {
                if (c == separator && !inQuotes) {
                    result.add(field.toString());
                    field.setLength(0);
                } else {
                    field.append(c);
                }
            }

        }

        if (inQuotes) {
            pendingField = field.toString();
            isMultiLine = true;
        } else {
            result.add(field.toString());
        }

        return result;

    }

    private String[] joinArrays(String[] array1, String[] array2) {
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2))
                .toArray(String[]::new);
    }
}

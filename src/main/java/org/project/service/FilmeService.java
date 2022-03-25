package org.project.service;

import org.project.entity.Filme;
import org.project.entity.Winner;
import org.project.entity.WinnerJson;
import org.project.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class FilmeService implements ApplicationRunner {

    private static final Logger LOGGER = Logger.getLogger(FilmeService.class.getName());

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private CsvParseService csvParseService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Filme> filmes = csvParseService.csv();

        filmes.forEach(filme -> {
            filmeRepository.save(filme);
        });

    }

    public List<Filme> findAll() {
        return filmeRepository.findAll();
    }

    public List<Integer> findYears(String s) {
        return filmeRepository.findYears(s);
    }

    public List<String> findWinners() {
        return filmeRepository.findWinners();
    }
}


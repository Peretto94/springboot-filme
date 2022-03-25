package org.project.service;

import org.project.entity.Winner;
import org.project.entity.WinnerJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class WinnerService {

    private static final Logger LOGGER = Logger.getLogger(FilmeService.class.getName());

    @Autowired
    private FilmeService filmeService;

    public WinnerJson findWinners() {

        List<String> filmsWinners = filmeService.findWinners();

        List<Winner> winners = new ArrayList<>();

        filmsWinners.forEach(s -> {
            List<Integer> years = filmeService.findYears(s);

            Integer count = 1;

            Integer index = years.size();

            while (count < years.size()) {

//                index--;

                Integer anoMaior = years.get(count);

                Integer anoMenor = years.get(count-1);

                Integer diff = anoMaior - anoMenor;

                Winner winner = new Winner(s, diff, anoMenor, anoMaior);

                winners.add(winner);

                count++;
            }
        });

        return organizeWinners(winners);

    }

    private WinnerJson organizeWinners(List<Winner> winners) {

        WinnerJson winnerJson = new WinnerJson();

        List<Winner> minYear = new ArrayList<>();
        List<Winner> maxYear = new ArrayList<>();

        Integer minInterval = 2022;
        Integer maxInterval = 0;

        for (Winner winner : winners) {
            if (winner.getInterval() < minInterval) {
                minInterval = winner.getInterval();
                minYear.removeAll(winners);
                minYear.add(winner);
            } else if (winner.getInterval().equals(minInterval)) {
                minYear.add(winner);
            }

            if (winner.getInterval() > maxInterval) {
                maxInterval = winner.getInterval();
                maxYear.removeAll(winners);
                maxYear.add(winner);
            } else if (winner.getInterval().equals(maxInterval)) {
                maxYear.add(winner);
            }
        }

        winnerJson.setMin(minYear);
        winnerJson.setMax(maxYear);

        return winnerJson;
    }
}

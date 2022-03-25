package org.project.controller;

import org.project.entity.WinnerJson;
import org.project.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/winner")
public class WinnerController {

    @Autowired
    private WinnerService winnerService;

    @GetMapping(value = "/result")
    public WinnerJson findWinnersResult() {
        return winnerService.findWinners();
    }

}

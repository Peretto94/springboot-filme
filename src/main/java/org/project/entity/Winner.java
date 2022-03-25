package org.project.entity;

import lombok.Data;

@Data
public class Winner {

    private String producer;

    private Integer interval;

    private Integer previousWin;

    private Integer followingWin;

    public Winner() {
    }

    public Winner(String producer, Integer interval, Integer previousWin, Integer followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }
}

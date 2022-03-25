package org.project.entity;

import lombok.Data;

import java.util.List;

@Data
public class WinnerJson {

    private List<Winner> min;

    private List<Winner> max;

    public WinnerJson() {
    }

    public WinnerJson(List<Winner> min, List<Winner> max) {
        this.min = min;
        this.max = max;
    }
}

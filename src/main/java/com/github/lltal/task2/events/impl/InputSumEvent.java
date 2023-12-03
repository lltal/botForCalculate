package com.github.lltal.task2.events.impl;

import com.github.lltal.task2.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class InputSumEvent implements Event<Double> {
    private Double inputSum;
    private static final String NEXT_EVENT = "Введите процент риска";

    @Override
    public Double getValue() {
        return inputSum;
    }

    @Override
    public String nextEvent() {
        return NEXT_EVENT;
    }
}

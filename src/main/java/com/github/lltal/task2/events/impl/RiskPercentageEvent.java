package com.github.lltal.task2.events.impl;

import com.github.lltal.task2.events.Event;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RiskPercentageEvent implements Event<Double> {
    private static final String NEXT_EVENT = "Введите текущий баланс";
    private Double riskPercentage;

    @Override
    public String nextEvent() {
        return NEXT_EVENT;
    }

    @Override
    public Double getValue() {
        return riskPercentage;
    }
}
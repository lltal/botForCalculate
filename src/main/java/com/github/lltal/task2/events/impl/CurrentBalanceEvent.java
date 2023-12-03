package com.github.lltal.task2.events.impl;

import com.github.lltal.task2.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class CurrentBalanceEvent implements Event<Double> {
    private Double currentBalance;

    @Override
    public String nextEvent() {
        return null;
    }

    @Override
    public Double getValue() {
        return currentBalance;
    }
}

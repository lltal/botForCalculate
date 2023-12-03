package com.github.lltal.task2.events.impl;

import com.github.lltal.task2.UI.Currencies;
import com.github.lltal.task2.events.Event;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CurrencyTypeEvent implements Event<Currencies> {

    private static final String NEXT_EVENT = "Введите сумму вхождения";

    private Currencies currencyType;

    @Override
    public String nextEvent() {
        return NEXT_EVENT;
    }

    @Override
    public Currencies getValue() {
        return currencyType;
    }
}

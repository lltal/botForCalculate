package com.github.lltal.task2.producers.impl;

import com.github.lltal.task2.UI.Currencies;
import com.github.lltal.task2.events.Event;
import com.github.lltal.task2.events.impl.CurrencyTypeEvent;
import com.github.lltal.task2.producers.EventProducer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 0)
public class CurrencyTypeEventProducer implements EventProducer<Currencies> {
    @Override
    public Event<Currencies> produce(Currencies value) {
        return new CurrencyTypeEvent(value);
    }
}

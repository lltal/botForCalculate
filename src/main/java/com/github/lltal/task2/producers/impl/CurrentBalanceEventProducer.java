package com.github.lltal.task2.producers.impl;

import com.github.lltal.task2.events.Event;
import com.github.lltal.task2.events.impl.CurrentBalanceEvent;
import com.github.lltal.task2.producers.EventProducer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 30)
public final class CurrentBalanceEventProducer implements EventProducer<Double> {
    @Override
    public Event<Double> produce(Double currentBalance) {
        return new CurrentBalanceEvent(currentBalance);
    }
}

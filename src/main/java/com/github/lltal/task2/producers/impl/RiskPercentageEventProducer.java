package com.github.lltal.task2.producers.impl;

import com.github.lltal.task2.events.Event;
import com.github.lltal.task2.events.impl.RiskPercentageEvent;
import com.github.lltal.task2.producers.EventProducer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 20)
public class RiskPercentageEventProducer implements EventProducer<Double> {
    @Override
    public Event<Double> produce(Double riskPercentage) {
        return new RiskPercentageEvent(riskPercentage);
    }
}

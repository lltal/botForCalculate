package com.github.lltal.task2.producers.impl;

import com.github.lltal.task2.events.Event;
import com.github.lltal.task2.events.impl.InputSumEvent;
import com.github.lltal.task2.producers.EventProducer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 10)
public class InputSumEventProducer implements EventProducer<Double> {
    @Override
    public Event<Double> produce(Double inputSum) {
        return new InputSumEvent(inputSum);
    }
}

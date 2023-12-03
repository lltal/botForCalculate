package com.github.lltal.task2.producers;

import com.github.lltal.task2.events.Event;

public interface EventProducer<T> {
    Event<T> produce(T value);


}

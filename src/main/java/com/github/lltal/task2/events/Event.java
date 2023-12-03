package com.github.lltal.task2.events;

public interface Event<T> {
    String nextEvent();
    T getValue();
}

package com.github.lltal.task2.repositories;

import com.github.lltal.task2.events.Event;

import java.util.List;

public interface EventRepository {
    void saveEvent(Long who, Event<?> event);

    List<Event<?>> getEvents(Long who);

    Event<?> getEvent(Long who, int eventIndex);
}
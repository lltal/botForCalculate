package com.github.lltal.task2.repositories.impl;

import com.github.lltal.task2.events.Event;
import com.github.lltal.task2.events.impl.CurrencyTypeEvent;
import com.github.lltal.task2.repositories.EventRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LocalEventRepository implements EventRepository {
    private final Map<Long, List<Event<?>>> eventsByChatId = new ConcurrentHashMap<>();

    @Override
    public void saveEvent(Long who, Event<?> event){
        if (event instanceof CurrencyTypeEvent){
            eventsByChatId.put(who, Collections.synchronizedList(new ArrayList<>()));
        }
        eventsByChatId.get(who).add(event);
    }

    @Override
    public List<Event<?>> getEvents(Long who){
        return eventsByChatId.get(who);
    }

    @Override
    public Event<?> getEvent(Long who, int eventIndex){
        return eventsByChatId.get(who).get(eventIndex);
    }
}

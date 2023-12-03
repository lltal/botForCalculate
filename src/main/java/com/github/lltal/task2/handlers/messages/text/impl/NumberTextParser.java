package com.github.lltal.task2.handlers.messages.text.impl;

import com.github.lltal.task2.events.Event;
import com.github.lltal.task2.handlers.messages.text.TextParser;
import com.github.lltal.task2.producers.EventProducer;
import com.github.lltal.task2.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.List;

@Component
public class NumberTextParser implements TextParser {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private List<EventProducer<Double>> eventProducers;

    @Override
    public List<? extends BotApiMethod<? extends Serializable>> parseText(Message message) {
        String text = message.getText();
        Long who = message.getFrom().getId();
        if(canParse(text)){
            int eventNumber = eventRepository.getEvents(who).size() - 1;

            Event<?> event = eventProducers.get(eventNumber).produce(Double.parseDouble(text));
            eventRepository.saveEvent(who, event);

            if (event.nextEvent() != null) {
                return List.of(SendMessage.builder()
                        .chatId(who.toString())
                        .text(event.nextEvent())
                        .build());
            } else {
                return null;
            }
        }
        return null;
    }

    private boolean canParse(String text){
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

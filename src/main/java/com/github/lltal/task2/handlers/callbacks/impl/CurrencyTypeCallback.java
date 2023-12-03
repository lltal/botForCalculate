package com.github.lltal.task2.handlers.callbacks.impl;

import com.github.lltal.task2.UI.Currencies;
import com.github.lltal.task2.events.Event;
import com.github.lltal.task2.events.impl.CurrencyTypeEvent;
import com.github.lltal.task2.handlers.callbacks.Callback;
import com.github.lltal.task2.repositories.EventRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;


import java.io.Serializable;
import java.util.*;

@Component
public class CurrencyTypeCallback implements Callback {
    private static final String CALLBACK_TYPE = "currency";
    private final Map<String, Currencies> currencies = new HashMap<>();
    @Autowired
    private EventRepository eventRepository;

    @PostConstruct
    private void init() {
        Arrays.stream(Currencies.values()).forEach(c -> {
            currencies.put(c.getCurrencyType(), c);
        });
    }

    @Override
    public List<? extends BotApiMethod<? extends Serializable>> executeCallback(CallbackQuery callbackQuery) {
        Long who = callbackQuery.getFrom().getId();

        List<Currencies> filteredCurrencies = currencies.entrySet()
                .stream()
                .filter(pair -> pair.getKey().equals(callbackQuery.getData()))
                .map(Map.Entry::getValue)
                .toList();

        Event<?> event = new CurrencyTypeEvent(filteredCurrencies.get(0));

        eventRepository.saveEvent(who, event);

        String queryId = callbackQuery.getId();

        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(queryId).build();

        SendMessage message = SendMessage.builder()
                .chatId(who.toString())
                .text(event.nextEvent())
                .build();

        return List.of(close, message);
    }

    @Override
    public String callbackType() {
        return CALLBACK_TYPE;
    }
}

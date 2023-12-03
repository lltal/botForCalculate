package com.github.lltal.task2.handlers.callbacks.impl;

import com.github.lltal.task2.UI.keybords.CurrenciesKeyboard;
import com.github.lltal.task2.handlers.callbacks.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.Serializable;
import java.util.List;

@Component
public class CalculateCallback implements Callback {

    private static final String CALLBACK_TYPE = "calculate";

    @Autowired
    private CurrenciesKeyboard currenciesKeyboard;

    @Override
    public List<? extends BotApiMethod<? extends Serializable>> executeCallback(CallbackQuery callbackQuery) {
        String queryId = callbackQuery.getId();
        Long who = callbackQuery.getFrom().getId();
        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(queryId).build();

        SendMessage message = currenciesKeyboard.getCurrenciesKeyboard(who);

        return List.of(close, message);
    }

    @Override
    public String callbackType() {
        return CALLBACK_TYPE;
    }
}
package com.github.lltal.task2.handlers.callbacks.impl;

import com.github.lltal.task2.UI.keybords.StartKeyboard;
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
public class AboutCallback implements Callback {
    private static final String CALLBACK_TYPE = "about";
    private static final String MESSAGE_TEXT = "Этот бот позволяет рассчитывать ордера";
    @Autowired
    private StartKeyboard startKeyboard;

    @Override
    public List<? extends BotApiMethod<? extends Serializable>> executeCallback(CallbackQuery callbackQuery) {
        Long who = callbackQuery.getFrom().getId();
        String queryId = callbackQuery.getId();

        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(queryId).build();

        SendMessage textMessage = SendMessage.builder()
                .chatId(who.toString())
                .text(MESSAGE_TEXT)
                .build();
        SendMessage keyboardToUser = startKeyboard.getMenu(who);

        return List.of(close, textMessage, keyboardToUser);
    }

    @Override
    public String callbackType() {
        return CALLBACK_TYPE;
    }
}

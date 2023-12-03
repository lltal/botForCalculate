package com.github.lltal.task2.handlers.callbacks;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.Serializable;
import java.util.List;

public interface Callback {
    List<? extends BotApiMethod<? extends Serializable>> executeCallback(CallbackQuery callbackQuery);

    @Autowired
    default void regMe(CallbackHandler handler){
        handler.registerCallback(callbackType(), this);
    }

    String callbackType();
}

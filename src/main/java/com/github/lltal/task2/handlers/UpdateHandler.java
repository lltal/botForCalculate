package com.github.lltal.task2.handlers;

import org.springframework.lang.Nullable;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.List;

public interface UpdateHandler {

    @Nullable
    List<? extends BotApiMethod<? extends Serializable>> handle(Update update);

}

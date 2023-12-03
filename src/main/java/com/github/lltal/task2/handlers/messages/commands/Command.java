package com.github.lltal.task2.handlers.messages.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {
    SendMessage execute(Message message);

    @Autowired
    default void regMe(CommandHandler handler){
        handler.registerCommand(commandType(), this);
    }
    String commandType();
}

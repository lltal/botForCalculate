package com.github.lltal.task2.handlers.messages.text;

import com.github.lltal.task2.handlers.UpdateHandler;
import com.github.lltal.task2.handlers.callbacks.Callback;
import com.github.lltal.task2.handlers.messages.commands.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.*;

@Component
public class SimpleTextHandler implements UpdateHandler {
    @Autowired
    private List<TextParser> parsers;

    @Override
    public List<? extends BotApiMethod<? extends Serializable>> handle(Update update) {
        if (update.hasMessage() && !update.getMessage().isCommand()) {
            Message message = update.getMessage();
            List<BotApiMethod<? extends Serializable>> methodsForExecution = new ArrayList<>();

            parsers.forEach(parser -> {
                List<? extends BotApiMethod<? extends Serializable>> candidateForExecution = parser.parseText(message);

                if(candidateForExecution != null){
                    methodsForExecution.addAll(candidateForExecution);
                }
            });
            return !methodsForExecution.isEmpty() ?
                    methodsForExecution :
                    null;
        } else {
            return null;
        }
    }
}

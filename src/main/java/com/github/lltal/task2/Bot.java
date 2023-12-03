package com.github.lltal.task2;

import com.github.lltal.task2.finishers.Finisher;
import com.github.lltal.task2.handlers.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "BOT_TOKEN_2";
    @Autowired
    private List<UpdateHandler> handlers;
    @Autowired
    private List<Finisher> finishers;

    @Override
    public String getBotUsername() {
        return "Task2Bot";
    }

    @Override
    public String getBotToken() {
        return System.getenv(BOT_TOKEN);
    }

    @Override
    public void onUpdateReceived(Update update) {
        for (UpdateHandler handler : handlers){
            if(tryToExecute(handler.handle(update))){
                return;
            }
        }

        for (Finisher finisher : finishers){
            tryToExecute(finisher.finish(update));
        }
    }

    private boolean tryToExecute(List<? extends BotApiMethod<? extends Serializable>> candidatesForExecution){
        if (candidatesForExecution != null) {
            candidatesForExecution.forEach(candidate -> {
                try {
                    execute(candidate);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });
            return true;
        }
        return false;
    }
}

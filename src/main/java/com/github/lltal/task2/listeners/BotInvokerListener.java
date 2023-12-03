package com.github.lltal.task2.listeners;

import com.github.lltal.task2.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotInvokerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Bot bot;
    @Autowired
    private TelegramBotsApi botsApi;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

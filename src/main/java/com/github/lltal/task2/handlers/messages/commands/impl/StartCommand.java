package com.github.lltal.task2.handlers.messages.commands.impl;

import com.github.lltal.task2.UI.keybords.StartKeyboard;
import com.github.lltal.task2.handlers.messages.commands.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class StartCommand implements Command {
    private static final String COMMAND_TYPE = "/start";
    @Autowired
    private StartKeyboard startKeyboard;

    @Override
    public SendMessage execute(Message message) {
        Long who = message.getFrom().getId();
        return startKeyboard.getMenu(who);
    }

    @Override
    public String commandType() {
        return COMMAND_TYPE;
    }
}

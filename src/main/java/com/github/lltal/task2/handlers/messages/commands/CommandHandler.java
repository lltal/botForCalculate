package com.github.lltal.task2.handlers.messages.commands;

import com.github.lltal.task2.handlers.UpdateHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandHandler implements UpdateHandler {
    private final Map<String, Command> commands = new HashMap<>();
    public void registerCommand(String commandType, Command command){
        commands.put(commandType, command);
    }

    @Override
    public List<BotApiMethod<? extends Serializable>> handle(Update update) {
        if (update.hasMessage() && update.getMessage().isCommand()) {
            Message message = update.getMessage();
            String[] messageText = message.getText().split(" ");
            Command command = commands.get(messageText[0]);
            return List.of(command.execute(message));
        } else {
            return null;
        }
    }
}

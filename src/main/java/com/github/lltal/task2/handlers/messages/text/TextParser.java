package com.github.lltal.task2.handlers.messages.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.List;

public interface TextParser {

    List<? extends BotApiMethod<? extends Serializable>> parseText(Message message);

}

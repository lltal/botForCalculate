package com.github.lltal.task2.finishers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.List;

public interface Finisher {
    List<? extends BotApiMethod<? extends Serializable>> finish(Update update);
}

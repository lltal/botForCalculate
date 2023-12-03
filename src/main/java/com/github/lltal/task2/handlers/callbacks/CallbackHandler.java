package com.github.lltal.task2.handlers.callbacks;

import com.github.lltal.task2.Bot;
import com.github.lltal.task2.handlers.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CallbackHandler implements UpdateHandler {

    private final Map<String, Callback> callbacks = new HashMap<>();

    public void registerCallback(String callbackType, Callback callback){
        callbacks.put(callbackType, callback);
    }

    @Override
    public List<? extends BotApiMethod<? extends Serializable>> handle(Update update) {
        if(update.hasCallbackQuery()){
            CallbackQuery cq = update.getCallbackQuery();
            Callback callback = callbacks.get(cq.getData().split(" ")[0]);
            return callback.executeCallback(cq);
        } else {
            return null;
        }
    }
}

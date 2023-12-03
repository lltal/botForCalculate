package com.github.lltal.task2.security;

import com.github.lltal.task2.security.AdminRights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class AccessManager {
    private static final String DENIED = "У тебя нет прав на совершение этого действия";
    @Autowired
    private AdminRights adminRights;

    public boolean tryToGetAccess(User user){
        return adminRights.getAdmins().contains(user.getUserName());
    }

    public SendMessage accessDenied(User user){
        return SendMessage.builder()
                .chatId(user.getId().toString())
                .text(DENIED)
                .build();
    }
}

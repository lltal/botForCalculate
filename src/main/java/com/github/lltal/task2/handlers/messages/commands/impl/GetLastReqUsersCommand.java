package com.github.lltal.task2.handlers.messages.commands.impl;

import com.github.lltal.task2.domain.CustomUser;
import com.github.lltal.task2.handlers.messages.commands.Command;
import com.github.lltal.task2.security.AccessManager;
import com.github.lltal.task2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Collection;

@Component
public class GetLastReqUsersCommand implements Command {
    private static final String COMMAND_TYPE = "/get_last_req_user";

    private static final int USER_AMOUNT = 5;
    @Autowired
    private UserService userService;
    @Autowired
    private AccessManager accessManager;

    @Override
    public SendMessage execute(Message message) {
        User principalUser = message.getFrom();
        if(accessManager.tryToGetAccess(principalUser)){
            Collection<CustomUser> users = userService.getLastUsage(USER_AMOUNT);

            StringBuilder sb = new StringBuilder();
            users.forEach(user -> {
                sb.append(createUserInfo(user));
            });

            return SendMessage.builder()
                    .chatId(principalUser.getId().toString())
                    .text(sb.toString())
                    .build();
        } else {
            return accessManager.accessDenied(principalUser);
        }
    }

    @Override
    public String commandType() {
        return COMMAND_TYPE;
    }

    private String createUserInfo(CustomUser user){
        return String.format("%s %s\nhttps://t.me/%s\n\n",
                user.getFirstName(),
                user.getLastName(),
                user.getUserName());
    }
}

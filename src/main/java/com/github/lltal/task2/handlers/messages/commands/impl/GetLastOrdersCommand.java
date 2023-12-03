package com.github.lltal.task2.handlers.messages.commands.impl;

import com.github.lltal.task2.domain.CalculationResult;
import com.github.lltal.task2.handlers.messages.commands.Command;
import com.github.lltal.task2.security.AccessManager;
import com.github.lltal.task2.security.AdminRights;
import com.github.lltal.task2.services.CalculationResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Collection;

@Component
public class GetLastOrdersCommand implements Command {
    private static final String COMMAND_TYPE = "/get_last_orders";
    private static final int OPERATIONS_AMOUNT = 5;
    @Autowired
    private CalculationResultService calculationResultService;
    @Autowired
    private AccessManager accessManager;

    @Override
    public SendMessage execute(Message message) {
        User principalUser = message.getFrom();
        if(accessManager.tryToGetAccess(principalUser)) {
            Collection<CalculationResult> operations = calculationResultService.getLastOperation(OPERATIONS_AMOUNT);

            StringBuilder sb = new StringBuilder();
            operations.forEach(operation -> {
                sb.append(createOperationInfo(operation));
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

    private String createOperationInfo(CalculationResult result){
        return String.format("%s %s\n%s\n\n",
                result.getUser().getFirstName(),
                result.getUser().getLastName(),
                result.getCalculationResult());
    }
}

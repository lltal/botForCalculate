package com.github.lltal.task2.finishers;

import com.github.lltal.task2.UI.Currencies;
import com.github.lltal.task2.UI.keybords.StartKeyboard;
import com.github.lltal.task2.domain.CalculationResult;
import com.github.lltal.task2.domain.CustomUser;
import com.github.lltal.task2.events.Event;
import com.github.lltal.task2.finishers.utils.Calculator;
import com.github.lltal.task2.repositories.EventRepository;
import com.github.lltal.task2.services.CalculationResultService;
import com.github.lltal.task2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class SessionFinisher implements Finisher{
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CalculationResultService calculationResultService;
    @Autowired
    private Calculator calculator;
    @Autowired
    private StartKeyboard startKeyboard;

    @Override
    public List<? extends BotApiMethod<? extends Serializable>> finish(Update update) {
        if(update.hasMessage()){
            User user = update.getMessage().getFrom();
            Long who = user.getId();
            List<Event<?>> events = eventRepository.getEvents(who);
            Double result = calculator.calculate(events);

            String textToCustomUser = createTextToCustomUser(events, result);

            SendMessage lastMessage = createLastMessage(who, textToCustomUser);
            SendMessage startMessage = startKeyboard.getMenu(who);

            CustomUser userFromDb = userService.save(user);
            calculationResultService.save(new CalculationResult(userFromDb, textToCustomUser));

            return List.of(lastMessage, startMessage);
        }
        return null;
    }

    private SendMessage createLastMessage(Long who, String text){
        return SendMessage.builder()
                .chatId(who.toString())
                .text(text)
                .build();
    }

    private String createTextToCustomUser(List<Event<?>> events, double result){
        return String.format(
                "Расчет:\n" +
                        "Тип расчета по %s\n" +
                        "Баланс: %f\n" +
                        "Риск: %f\n" +
                        "Вход: %f\n" +
                        "Результат: %f",
                ((Currencies)(events.get(0).getValue())).getCurrencyView(),
                ((Double)(events.get(1).getValue())),
                ((Double)(events.get(2).getValue())),
                ((Double)(events.get(3).getValue())),
                result);
    }
}

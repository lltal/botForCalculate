package com.github.lltal.task2.UI.keybords;

import com.github.lltal.task2.UI.AbstractKeyboard;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class StartKeyboard extends AbstractKeyboard {
    private static final String CALCULATE_BUTTON_TEXT = "Рассчитать стоимость";
    private static final String CALCULATE_BUTTON_CALLBACK_DATA = "calculate";
    private static final String ABOUT_BUTTON_TEXT = "О боте";
    private static final String ABOUT_BUTTON_CALLBACK_DATA = "about";
    private InlineKeyboardMarkup startMarkup;

    @PostConstruct
    private void init(){
        InlineKeyboardButton calculate = createInlineButton(CALCULATE_BUTTON_TEXT, CALCULATE_BUTTON_CALLBACK_DATA);
        InlineKeyboardButton about = createInlineButton(ABOUT_BUTTON_TEXT, ABOUT_BUTTON_CALLBACK_DATA);
        this.startMarkup = createInlineMarkup(List.of(List.of(calculate, about)));
    }

    public SendMessage getMenu(Long who){
        return SendMessage.builder()
                .chatId(who.toString())
                .parseMode("HTML")
                .text("<b> Выберете действие </b>")
                .replyMarkup(startMarkup)
                .build();
    }
}

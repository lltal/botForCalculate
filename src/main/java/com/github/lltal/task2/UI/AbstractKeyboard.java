package com.github.lltal.task2.UI;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public abstract class AbstractKeyboard {
    protected InlineKeyboardButton createInlineButton(String text, String callbackData) {
        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build();
    }

    protected InlineKeyboardMarkup createInlineMarkup(List<List<InlineKeyboardButton>> keyboardRows){
        InlineKeyboardMarkup.InlineKeyboardMarkupBuilder builder = InlineKeyboardMarkup.builder();
        for (List<InlineKeyboardButton> keyboardRow : keyboardRows){
            builder.keyboardRow(keyboardRow);
        }
        return builder.build();
    }
}

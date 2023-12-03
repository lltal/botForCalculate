package com.github.lltal.task2.UI.keybords;

import com.github.lltal.task2.UI.AbstractKeyboard;
import com.github.lltal.task2.UI.Currencies;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.List;

@Component
public class CurrenciesKeyboard extends AbstractKeyboard {
    private InlineKeyboardMarkup keyboardMarkup;
    private static final String header = "Выберете валюту";
    @PostConstruct
    private void init() {
        List<InlineKeyboardButton> inlineButtons = Arrays.stream(Currencies.values())
                .map(currency ->
                        createInlineButton(
                                currency.getCurrencyView(),
                                currency.getCurrencyType()))
                .toList();
        this.keyboardMarkup = createInlineMarkup(List.of(inlineButtons));
    }

    public SendMessage getCurrenciesKeyboard(Long who){
        return SendMessage.builder()
                .chatId(who.toString())
                .text(header)
                .replyMarkup(keyboardMarkup)
                .build();
    }
}

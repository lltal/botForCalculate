package com.github.lltal.task2.UI;

import com.github.lltal.task2.appliers.CurrencyFunctionApplier;
import lombok.Getter;

@Getter
public enum Currencies {
    RUB(
            (inputSum, riskPercentage, currentBalance) ->
                    currentBalance / 7 * (riskPercentage / 100) + inputSum,
            "Рубль",
            "currency rub"),
    USD(
            (inputSum, riskPercentage, currentBalance) ->
                    currentBalance / 10 * (riskPercentage / 100) + inputSum,
            "Доллар",
            "currency usd"),
    BTC(
            (inputSum, riskPercentage, currentBalance) ->
                    currentBalance / 30 * (riskPercentage / 100) + inputSum / 2,
            "Биткоин",
            "currency btc");

    private final String currencyView;
    private final String currencyType;
    private final CurrencyFunctionApplier<Double, Double, Double> applier;

    Currencies(
            CurrencyFunctionApplier<Double, Double, Double> applier,
            String currencyView,
            String currencyType
    ) {
        this.applier = applier;
        this.currencyView = currencyView;
        this.currencyType = currencyType;
    }
}
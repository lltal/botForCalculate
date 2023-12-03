package com.github.lltal.task2.finishers.utils;

import com.github.lltal.task2.UI.Currencies;
import com.github.lltal.task2.appliers.CurrencyFunctionApplier;
import com.github.lltal.task2.events.Event;
import com.github.lltal.task2.events.impl.CurrencyTypeEvent;
import com.github.lltal.task2.events.impl.CurrentBalanceEvent;
import com.github.lltal.task2.events.impl.InputSumEvent;
import com.github.lltal.task2.events.impl.RiskPercentageEvent;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

public class Result {
    private CurrencyTypeEvent currencyEvent;
    private CurrentBalanceEvent balanceEvent;
    private InputSumEvent sumEvent;
    private RiskPercentageEvent riskEvent;
    private Map<Class<?>, Field> fieldsByClass = new HashMap<>();


    public Result() {
        Field[] fields = getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            if (!field.getType().equals(Map.class)){
                fieldsByClass.put(field.getType(), field);
            }
        });
    }

    @SneakyThrows
    public void fillFields(List<Event<?>> events){
        for (Event<?> event : events){
            Field field = fieldsByClass.get(event.getClass());
            if (field != null){
                field.setAccessible(true);
                field.set(this, event);
            }
        }
    }

    public Double calculateResult(){
        CurrencyFunctionApplier<Double, Double, Double> applier = currencyEvent.getValue().getApplier();
        return applier.apply(
                sumEvent.getValue(),
                riskEvent.getValue(),
                balanceEvent.getValue());
    }
}

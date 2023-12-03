package com.github.lltal.task2.finishers.utils;

import com.github.lltal.task2.events.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Calculator {

    public Double calculate(List<Event<?>> events){
        Result result = new Result();
        result.fillFields(events);
        return result.calculateResult();
    }
}

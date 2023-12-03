package com.github.lltal.task2.appliers;

public interface CurrencyFunctionApplier<T, V, F> {
    Double apply(T inputSum, V riskPercentage, F currentBalance);
}

package com.brenohq.caju_authorization.mapper;

import com.brenohq.caju_authorization.model.Account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BalanceMapper {
    private static final Map<String, Function<Account, BigDecimal>> balanceGetters = new HashMap<>();
    private static final Map<String, BiConsumer<Account, BigDecimal>> balanceSetters = new HashMap<>();

    static {
        // Mapping getters to each MCC
        balanceGetters.put("5411", Account::getFoodBalance);
        balanceGetters.put("5412", Account::getFoodBalance);
        balanceGetters.put("5811", Account::getMealBalance);
        balanceGetters.put("5812", Account::getMealBalance);

        // Defaults to cash balance
        balanceGetters.put("default", Account::getCashBalance);

        // Mapping setters to each MCC
        balanceSetters.put("5411", Account::setFoodBalance);
        balanceSetters.put("5412", Account::setFoodBalance);
        balanceSetters.put("5811", Account::setMealBalance);
        balanceSetters.put("5812", Account::setMealBalance);

        // Defaults to cash balance
        balanceSetters.put("default", Account::setCashBalance);
    }

    public static Function<Account, BigDecimal> getGetter(String mcc) {
        return balanceGetters.getOrDefault(mcc, balanceGetters.get("default"));
    }

    public static BiConsumer<Account, BigDecimal> getSetter(String mcc) {
        return balanceSetters.getOrDefault(mcc, balanceSetters.get("default"));
    }
}

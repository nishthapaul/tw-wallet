package com.tw;

import com.tw.exceptions.InsufficientMoneyException;
import com.tw.exceptions.NegativeValueException;
import com.tw.exceptions.ZeroValueException;

import java.util.List;

public class Wallet {

    private Money money;

    public Wallet(List<Money> moneyList) throws NegativeValueException, ZeroValueException {
        money = Money.createRupee(0);
        for (Money m : moneyList) {
            money = money.add(m);
        }
    }

    public Money withdraw(Money other) throws InsufficientMoneyException, ZeroValueException {
        money = money.subtract(other);
        return other;
    }

    public Money totalAmount(Currency currency) {
        return money.convertTo(currency);
    }

}

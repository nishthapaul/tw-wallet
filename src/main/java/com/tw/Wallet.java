package com.tw;

import com.tw.exceptions.InsufficientMoneyException;
import com.tw.exceptions.NegativeValueException;
import com.tw.exceptions.ZeroValueException;

public class Wallet {

    private Money money;

    public Wallet() throws NegativeValueException {
        money = Money.createRupee(0);
    }

    public Money withdraw(Money other) throws InsufficientMoneyException, ZeroValueException {
        money = money.subtract(other);
        return other;
    }

    public void deposit(Money other) throws ZeroValueException {
        money = money.add(other);
    }

    public Money totalAmount(Currency currency) {
        return money.convertTo(currency);
    }
}

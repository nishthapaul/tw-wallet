package com.tw;

import com.tw.exceptions.InsufficientMoneyException;
import com.tw.exceptions.NegativeValueException;
import com.tw.exceptions.ZeroValueException;

public class Wallet {
    private int money;

    public Wallet(int money) {
        this.money = money;
    }

    public int withdraw(int amount) throws ZeroValueException, NegativeValueException, InsufficientMoneyException {
        if (amount == 0) throw new ZeroValueException();
        if (amount < 0) throw new NegativeValueException();
        if (amount > money) throw new InsufficientMoneyException();
        money -= amount;
        return amount;
    }

    public int totalAmount() {
        return money;
    }
}

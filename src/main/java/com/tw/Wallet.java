package com.tw;

import com.tw.exceptions.InsufficientMoneyException;

public class Wallet {

    private Money money;

    public Wallet() {
    }

    public Money withdraw(Money money) throws InsufficientMoneyException {
        if (this.money == null) {
            throw new InsufficientMoneyException();
        }
        if (this.money.isLessThan(money)) {
            throw new InsufficientMoneyException();
        }
        this.money = this.money.subtract(money);
        return money;
    }

    public void deposit(Money money) {
        if (this.money == null) {
            this.money = money;
        } else {
            this.money = this.money.add(money);
        }
    }

    public Money totalAmount(Currency currency) {
        return money.convertTo(currency);
    }
}

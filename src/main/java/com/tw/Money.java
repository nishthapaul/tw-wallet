package com.tw;

import com.tw.exceptions.InsufficientMoneyException;
import com.tw.exceptions.NegativeValueException;
import com.tw.exceptions.ZeroValueException;

public class Money {
    private final Currency currency;
    private double value;

    private Money(Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public static Money createMoney(Currency currency, double value) throws NegativeValueException, ZeroValueException {
        if (value == 0) {
            throw new ZeroValueException();
        }
        if (value < 0) {
            throw new NegativeValueException();
        }
        return new Money(currency, value);
    }

    public Money add(Money money) {
        Money convertedMoney = money.convertTo(this.currency);

        this.value += convertedMoney.value;

        return this;
    }

    public Money subtract(Money money) throws InsufficientMoneyException {
        Money convertedMoney = money.convertTo(this.currency);

        if (this.isLessThan(convertedMoney)) throw new InsufficientMoneyException();
        this.value -= convertedMoney.value;

        return this;
    }

    public boolean isLessThan(Money money) {
        return this.value < money.value;
    }

    public Money convertTo(Currency currency) {
        return new Money(currency, this.currency.convertTo(currency, this.value));
    }

    @Override
    public String toString() {
        return this.currency + " " + this.value;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Money otherMoney = (Money) obj;
        return this.currency == otherMoney.currency && Math.abs(this.value - otherMoney.value) < 0.001;
    }

}

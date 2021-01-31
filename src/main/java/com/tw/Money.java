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

    public static Money createRupee(double value) throws NegativeValueException, ZeroValueException {
        if (value == 0) {
            throw new ZeroValueException();
        }
        if (value < 0) {
            throw new NegativeValueException();
        }
        return new Money(Currency.RUPEE, value);
    }

    public static Money createDollar(double value) throws NegativeValueException, ZeroValueException {
        if (value == 0) {
            throw new ZeroValueException();
        }
        if (value < 0) {
            throw new NegativeValueException();
        }
        return new Money(Currency.DOLLAR, value);
    }

    public Money add(Money otherMoney) {
        Money oneAddend = this.convertTo(Currency.RUPEE);
        Money secondAddend = otherMoney.convertTo(Currency.RUPEE);

        oneAddend.value += secondAddend.value;

        return oneAddend.convertTo(this.currency);
    }

    public Money subtract(Money otherMoney) throws InsufficientMoneyException {
        Money oneAddend = this.convertTo(Currency.RUPEE);
        Money secondAddend = otherMoney.convertTo(Currency.RUPEE);

        if (oneAddend.isLessThan(secondAddend)) throw new InsufficientMoneyException();

        oneAddend.value -= secondAddend.value;

        return oneAddend.convertTo(this.currency);
    }

    public boolean isLessThan(Money money) {
        return this.value < money.value;
    }

    public Money convertTo(Currency currency) {
        Money convertedMoneyInRupee = new Money(Currency.RUPEE, this.currency.convertToBaseCurrency(this.value));
        if (currency == Currency.RUPEE) return convertedMoneyInRupee;
        else
            return new Money(currency, convertedMoneyInRupee.currency.convertToNonBaseCurrency(currency, convertedMoneyInRupee.value));
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

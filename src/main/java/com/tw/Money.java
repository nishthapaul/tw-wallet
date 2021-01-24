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

    public static Money createRupee(double value) throws NegativeValueException {
        if (value < 0) {
            throw new NegativeValueException();
        }
        return new Money(Currency.RUPEE, value);
    }

    public static Money createDollar(double value) throws NegativeValueException {
        if (value < 0) {
            throw new NegativeValueException();
        }
        return new Money(Currency.DOLLAR, value);
    }

    public Money add(Money otherMoney) throws ZeroValueException {
        Money oneAddend = this.convertTo(Currency.RUPEE);
        Money secondAddend = otherMoney.convertTo(Currency.RUPEE);
        if (secondAddend.value == 0) throw new ZeroValueException("deposit");
        oneAddend.value += secondAddend.value;
        return oneAddend.convertTo(this.currency);
    }

    public Money subtract(Money otherMoney) throws InsufficientMoneyException, ZeroValueException {
        Money oneAddend = this.convertTo(Currency.RUPEE);
        System.out.println(oneAddend);
        Money secondAddend = otherMoney.convertTo(Currency.RUPEE);
        System.out.println(otherMoney);
        if (secondAddend.value > oneAddend.value) throw new InsufficientMoneyException();
        if (secondAddend.value == 0) throw new ZeroValueException("withdraw");
        oneAddend.value -= secondAddend.value;
        return oneAddend.convertTo(this.currency);
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

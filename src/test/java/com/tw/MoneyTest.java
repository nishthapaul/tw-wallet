package com.tw;

import com.tw.exceptions.NegativeValueException;
import com.tw.exceptions.ZeroValueException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {

    @Test
    void shouldNotBeAbleToCreateNegativeValuedMoney() {
        assertThrows(NegativeValueException.class, () -> Money.createRupee(-5));
    }

    @Test
    void convertRupeeToDollar() throws NegativeValueException {
        Money oneRupee = Money.createRupee(1);
        Money oneRupeeInDollars = Money.createDollar(0.0133);

        assertThat(oneRupee.convertTo(Currency.DOLLAR), is(equalTo(oneRupeeInDollars)));
    }

    @Test
    void convertRupeeToRupee() throws NegativeValueException {
        Money oneRupee = Money.createRupee(1);

        assertThat(oneRupee.convertTo(Currency.RUPEE), is(equalTo(oneRupee)));
    }

    @Test
    void convertDollarToRupee() throws NegativeValueException {
        Money oneDollar = Money.createDollar(1);
        Money oneDollarInRupees = Money.createRupee(74.85);

        assertThat(oneDollar.convertTo(Currency.RUPEE), is(equalTo(oneDollarInRupees)));
    }

    @Test
    void convertDollarToDollar() throws NegativeValueException {
        Money oneDollar = Money.createDollar(74.85);

        assertThat(oneDollar.convertTo(Currency.DOLLAR), is(equalTo(oneDollar)));
    }

    @Test
    void addRupeeToDollar() throws NegativeValueException, ZeroValueException {
        Money oneRupee = Money.createRupee(1);
        Money oneDollar = Money.createDollar(1);

        Money sum = oneRupee.add(oneDollar);

        assertThat(sum, is(equalTo(Money.createRupee(75.85))));
    }

    @Test
    void addRupeeToRupee() throws NegativeValueException, ZeroValueException {
        Money oneRupee = Money.createRupee(1);
        Money tenRupee = Money.createRupee(10);

        Money sum = oneRupee.add(tenRupee);

        assertThat(sum, is(equalTo(Money.createRupee(11))));
    }

    @Test
    void addDollarToRupee() throws NegativeValueException, ZeroValueException {
        Money oneRupee = Money.createRupee(1);
        Money oneDollar = Money.createDollar(1);

        Money sum = oneDollar.add(oneRupee);

        assertThat(sum, is(equalTo(Money.createDollar(1.0133))));
    }

}
package com.tw;

import com.tw.exceptions.InsufficientMoneyException;
import com.tw.exceptions.NegativeValueException;
import com.tw.exceptions.ZeroValueException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WalletTest {

    @Test
    void shouldReturnSpecifiedAmountOfMoneyFromWallet() throws NegativeValueException, InsufficientMoneyException, ZeroValueException {
        Money fiveRupee = Money.createRupee(5);
        Wallet wallet = new Wallet();
        wallet.deposit(fiveRupee);

        Money twoRupee = Money.createRupee(2);
        Money withdrawnMoney = wallet.withdraw(twoRupee);

        assertThat(withdrawnMoney, is(equalTo(twoRupee)));

    }


    @Test
    void shouldReflectChangeInWalletMoneyAfterWithdrawing() throws NegativeValueException, InsufficientMoneyException, ZeroValueException {
        Money fiveRupee = Money.createRupee(5);
        Wallet wallet = new Wallet();
        wallet.deposit(fiveRupee);

        Money twoRupee = Money.createRupee(2);
        wallet.withdraw(twoRupee);

        Money totalAmount = wallet.totalAmount(Currency.RUPEE);
        Money threeRupee = Money.createRupee(3);
        assertThat(totalAmount, is(equalTo(threeRupee)));

    }

    @Test
    void shouldNotBeAbleToWithdrawAmountMoreThanWalletTotalMoney() throws NegativeValueException, ZeroValueException {
        Money fiveRupee = Money.createRupee(5);
        Wallet wallet = new Wallet();
        wallet.deposit(fiveRupee);

        Money sevenRupee = Money.createRupee(7);
        assertThrows(InsufficientMoneyException.class, () -> wallet.withdraw(sevenRupee));
    }


    @Test
    void shouldNotBeAbleToWithdrawZeroValuedAmount() throws NegativeValueException, ZeroValueException {
        Money fiveRupee = Money.createRupee(5);
        Wallet wallet = new Wallet();
        wallet.deposit(fiveRupee);

        Money zeroRupee = Money.createRupee(0);
        assertThrows(ZeroValueException.class, () -> wallet.withdraw(zeroRupee));
    }

    @Test
    void shouldReturnCorrectTotalAmountOfWalletMoneyInRupees() throws NegativeValueException, ZeroValueException {
        Money fiftyRupee = Money.createRupee(50);
        Money oneDollar = Money.createDollar(1);
        Wallet wallet = new Wallet();
        wallet.deposit(fiftyRupee);
        wallet.deposit(oneDollar);

        Money totalAmount = wallet.totalAmount(Currency.RUPEE);

        assertThat(totalAmount, is(equalTo(Money.createRupee(124.85))));

    }

    @Test
    void shouldReturnCorrectTotalAmountOfWalletMoneyInDollars() throws NegativeValueException, ZeroValueException {
        Money aRupee = Money.createRupee(74.85);
        Money anotherRupee = Money.createRupee(149.7);
        Money oneDollar = Money.createDollar(1);
        Wallet wallet = new Wallet();
        wallet.deposit(aRupee);
        wallet.deposit(anotherRupee);
        wallet.deposit(oneDollar);

        Money totalAmount = wallet.totalAmount(Currency.DOLLAR);

        assertThat(totalAmount, is(equalTo(Money.createDollar(4))));

    }

}

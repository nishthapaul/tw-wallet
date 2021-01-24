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
    void shouldReturnSpecifiedAmountOfMoneyFromWallet_IfTheRequestedAmountIsLessThanOrEqualToWalletMoney() {
        Wallet wallet = new Wallet(5);

        try {
            int amountWithdrawn = wallet.withdraw(2);
            assertThat(amountWithdrawn, is(equalTo(2)));
        } catch (ZeroValueException | NegativeValueException | InsufficientMoneyException e) {
            System.out.println(e);
        }
    }

    @Test
    void shouldReflectChangeInWalletMoneyAfterWithdrawing() {
        Wallet wallet = new Wallet(5);

        try {
            int amountWithdrawn = wallet.withdraw(2);
            assertThat(wallet.totalAmount(), is(equalTo(5 - amountWithdrawn)));
        } catch (ZeroValueException | NegativeValueException | InsufficientMoneyException e) {
            System.out.println(e);
        }
    }

    @Test
    void shouldNotBeAbleToWithdrawAmountMoreThanWalletTotalMoney() {
        Wallet wallet = new Wallet(5);

        assertThrows(InsufficientMoneyException.class, () -> wallet.withdraw(7));
    }

    @Test
    void shouldNotBeAbleToWithdrawZeroValuedAmount() {
        Wallet wallet = new Wallet(5);

        assertThrows(ZeroValueException.class, () -> wallet.withdraw(0));
    }

    @Test
    void shouldNotBeAbleToWithdrawNegativeValuedAmount() {
        Wallet wallet = new Wallet(5);

        assertThrows(NegativeValueException.class, () -> wallet.withdraw(-2));
    }

}

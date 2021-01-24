package com.tw;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WalletTest {
    @Test
    void shouldReturnSpecifiedAmountOfMoneyFromWallet() {
        Wallet wallet = new Wallet(5);

        int amountWithdrawn = wallet.withdraw(2);

        assertThat(amountWithdrawn, is(equalTo(2)));
    }

    @Test
    void shouldReflectChangeInWalletMoneyAfterWithdrawing() {
        Wallet wallet = new Wallet(5);

        int amountWithdrawn = wallet.withdraw(2);

        assertThat(wallet.totalAmount(), is(equalTo(5 - amountWithdrawn)));
    }

}

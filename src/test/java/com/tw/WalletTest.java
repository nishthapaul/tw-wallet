package com.tw;

import com.tw.exceptions.InsufficientMoneyException;
import com.tw.exceptions.InvalidMoneyException;
import org.junit.jupiter.api.Test;

import static com.tw.Money.createMoney;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WalletTest {

    @Test
    void shouldReturnSpecifiedAmountOfMoneyFromWallet() throws InsufficientMoneyException, InvalidMoneyException {
        Money fiveRupee = createMoney(Currency.RUPEE, 5);
        Wallet wallet = new Wallet();
        wallet.deposit(fiveRupee);

        Money twoRupee = createMoney(Currency.RUPEE, 2);
        Money withdrawnMoney = wallet.withdraw(twoRupee);

        assertThat(withdrawnMoney, is(equalTo(twoRupee)));
    }

    @Test
    void shouldReflectChangeInWalletMoneyAfterWithdrawing() throws InsufficientMoneyException, InvalidMoneyException {
        Money fiveRupee = createMoney(Currency.RUPEE, 5);
        Wallet wallet = new Wallet();
        wallet.deposit(fiveRupee);

        Money twoRupee = createMoney(Currency.RUPEE, 2);
        wallet.withdraw(twoRupee);

        Money totalAmount = wallet.totalAmount(Currency.RUPEE);
        Money threeRupee = createMoney(Currency.RUPEE, 3);
        assertThat(totalAmount, is(equalTo(threeRupee)));
    }

    @Test
    void shouldNotBeAbleToWithdrawAmountMoreThanWalletTotalMoney() throws InvalidMoneyException {
        Money fiveRupee = createMoney(Currency.RUPEE, 5);
        Wallet wallet = new Wallet();
        wallet.deposit(fiveRupee);

        Money sevenRupee = createMoney(Currency.RUPEE, 7);
        assertThrows(InsufficientMoneyException.class, () -> wallet.withdraw(sevenRupee));
    }

    @Test
    void shouldReturnCorrectTotalAmountOfWalletMoneyInRupees() throws InvalidMoneyException {
        Money fiftyRupee = createMoney(Currency.RUPEE, 50);
        Money oneDollar = createMoney(Currency.DOLLAR, 1);
        Wallet wallet = new Wallet();
        wallet.deposit(fiftyRupee);
        wallet.deposit(oneDollar);

        Money totalAmount = wallet.totalAmount(Currency.RUPEE);

        assertThat(totalAmount, is(equalTo(createMoney(Currency.RUPEE, 124.85))));
    }

    @Test
    void shouldReturnCorrectTotalAmountOfWalletMoneyInDollars() throws InvalidMoneyException {
        Money aRupee = createMoney(Currency.RUPEE, 74.85);
        Money anotherRupee = createMoney(Currency.RUPEE, 149.7);
        Money oneDollar = createMoney(Currency.DOLLAR, 1);
        Wallet wallet = new Wallet();
        wallet.deposit(aRupee);
        wallet.deposit(anotherRupee);
        wallet.deposit(oneDollar);

        Money totalAmount = wallet.totalAmount(Currency.DOLLAR);

        assertThat(totalAmount, is(equalTo(createMoney(Currency.DOLLAR, 4))));
    }

}

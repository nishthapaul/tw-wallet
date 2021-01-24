package com.tw;

public class Wallet {
    private int money;

    public Wallet(int money) {
        this.money = money;
    }

    public int withdraw(int amount) {
        money -= amount;
        return amount;
    }

    public int totalAmount() {
        return money;
    }
}

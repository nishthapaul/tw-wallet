package com.tw;

enum Currency {
    RUPEE(1), DOLLAR(74.85);

    private final double conversionFactor;

    Currency(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double convertToBaseCurrency(double amount) {
        return amount * conversionFactor;
    }

    public double convertToNonBaseCurrency(Currency currency, double amount) {
        return amount / currency.conversionFactor;
    }
}

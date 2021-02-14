package com.tw;

enum Currency {
    RUPEE(1), DOLLAR(74.85);

    private final double conversionFactor;

    Currency(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double convertTo(Currency currency, double amount) {
        if(currency == RUPEE) {
            return convertToBaseCurrency(amount);
        } else {
            return convertToNonBaseCurrency(currency, amount);
        }
    }

    private double convertToBaseCurrency(double amount) {
        return amount * conversionFactor;
    }

    private double convertToNonBaseCurrency(Currency currency, double amount) {
        return amount * ( this.conversionFactor / currency.conversionFactor);
    }
}

package com.tw;

enum Currency {
    RUPEE(1), DOLLAR(74.85), YEN(0.7);

    private final double conversionFactor;

    Currency(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double convertTo(Currency currency, double amount) {
        return amount * ( this.conversionFactor / currency.conversionFactor);
    }
}

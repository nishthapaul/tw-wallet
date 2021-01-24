package com.tw.exceptions;

public class NegativeValueException extends Throwable {
    @Override
    public String toString() {
        return "You cannot withdraw negative valued amount from the Wallet";
    }
}

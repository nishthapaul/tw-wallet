package com.tw.exceptions;

public class ZeroValueException extends Throwable {
    @Override
    public String toString() {
        return "You cannot withdraw zero amount from the wallet";
    }
}

package com.tw.exceptions;

public class ZeroValueException extends Throwable {
    String str;

    public ZeroValueException(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "You cannot " + str + " zero valued money";
    }
}

package com.tw.exceptions;

public class NegativeValueException extends Throwable {
    @Override
    public String toString() {
        return "Money cannot have negative value";
    }
}

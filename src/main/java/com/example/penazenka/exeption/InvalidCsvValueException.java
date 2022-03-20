package com.example.penazenka.exeption;

public class InvalidCsvValueException extends Exception {
    private String value;

    public InvalidCsvValueException(String errorMessage, String value) {
        super(errorMessage);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s, Value=[%s]", super.getMessage(), this.value);
    }
}

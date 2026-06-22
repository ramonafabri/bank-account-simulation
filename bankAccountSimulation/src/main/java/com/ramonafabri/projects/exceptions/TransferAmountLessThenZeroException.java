package com.ramonafabri.projects.exceptions;

public class TransferAmountLessThenZeroException extends RuntimeException {
    public TransferAmountLessThenZeroException(String message) {
        super(message);
    }
}

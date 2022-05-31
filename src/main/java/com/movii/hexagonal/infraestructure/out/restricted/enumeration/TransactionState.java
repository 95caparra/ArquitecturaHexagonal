package com.movii.hexagonal.infraestructure.out.restricted.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

public enum TransactionState {
    OK("APROBADA"),
    NOT_AUTHORIZED("RECHAZADA"),
    PENDING("PENDIENTE"),
    FAILED("FALLIDA");

    private String val;

    TransactionState(String val) {
        this.val = val;
    }

    public static Optional<TransactionState> resolve(String state) {
        return Stream.of(values())
                .filter(transactionState ->
                        transactionState.name().equals(state) || transactionState.value().equals(state))
                .findFirst();
    }

    public static boolean skipFinalize(TransactionState transactionState) {
        return (PENDING.equals(transactionState) || FAILED.equals(transactionState));
    }

    public static boolean isError(TransactionState transactionState) {
        return (NOT_AUTHORIZED.equals(transactionState) || FAILED.equals(transactionState));
    }

    public static boolean hasValueIn(String txStateValue, TransactionState... transactionStates) {
        return Stream.of(transactionStates).anyMatch(transactionState -> transactionState.value().equals(txStateValue));
    }

    public String value() {
        return val;
    }

}

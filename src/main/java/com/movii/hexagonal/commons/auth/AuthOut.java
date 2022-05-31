package com.movii.hexagonal.commons.auth;

import lombok.Data;

@Data
public class AuthOut {

    private final String nonce;
    private final String seed;
    private final String trankey;
}

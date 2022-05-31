package com.movii.hexagonal.commons.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthIn {

    private String nonce = "";
    private String seed  = "";
    private String trankey  = "";
}

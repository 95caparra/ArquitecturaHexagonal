package com.movii.hexagonal.commons.auth;

import javax.naming.AuthenticationException;
import java.util.Map;

public interface IAuthentication {

    AuthOut generateAuth();
    boolean signatureValidation(final Map<String, String> headers) throws AuthenticationException;
}

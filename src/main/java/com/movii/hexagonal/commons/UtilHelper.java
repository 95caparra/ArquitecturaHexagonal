package com.movii.hexagonal.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UtilHelper {

    public static final byte[] md5(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("MD5");
        return mDigest.digest(input.getBytes());
    }

    public static final byte[] sha1(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        return mDigest.digest(input);
    }

    public static final String base64(byte[] input) {
        byte[] encodedBytes = (Base64.getEncoder()).encode(input);
        return new String(encodedBytes);
    }

    public static final String base64ToString(String input) {
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        return new String(decodedBytes);
    }
}

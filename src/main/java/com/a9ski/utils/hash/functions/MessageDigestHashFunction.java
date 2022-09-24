package com.a9ski.utils.hash.functions;

import com.a9ski.utils.hash.HashFunction;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MessageDigestHashFunction implements HashFunction<String, String> {

    private final MessageDigest digest;

    public MessageDigestHashFunction(final MessageDigest digest){
        this.digest = digest;
    }


    @Override
    public String hash(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        final byte[] hashData = digest.digest(key.getBytes(StandardCharsets.UTF_8));
        return toHex(hashData);
    }

    protected String toHex(final byte[] hash) {
        final StringBuilder result = new StringBuilder(2 * hash.length);
        for (byte b: hash) {
            final String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) {
                result.append('0');
            }
            result.append(hex);
        }
        return result.toString();
    }
}

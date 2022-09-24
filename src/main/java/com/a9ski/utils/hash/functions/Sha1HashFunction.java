package com.a9ski.utils.hash.functions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1HashFunction extends MessageDigestHashFunction {
    public Sha1HashFunction() {
        super(createSha1());
    }

    public static MessageDigest createSha1() {
        try {
            return MessageDigest.getInstance("SHA-1");
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException("Cannot find SHA-1 algorithm", e);
        }
    }
}

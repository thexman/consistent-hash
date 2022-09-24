package com.a9ski.utils.hash.functions;

public class FunctionsFactory {
    private FunctionsFactory() {
        super();
    }
    public static Sha1HashFunction sha1() {
        return new Sha1HashFunction();
    }
}

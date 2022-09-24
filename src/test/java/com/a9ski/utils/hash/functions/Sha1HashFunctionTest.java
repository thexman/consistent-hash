package com.a9ski.utils.hash.functions;

import com.a9ski.utils.hash.HashFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Sha1HashFunctionTest {
    private final HashFunction<String, String> hashFunction = FunctionsFactory.sha1();

    @Test
    void testHash() {
        assertEquals("078bef09f3d800de4434b257891834a145844f82", hashFunction.hash("съобщение"));
    }

    @Test
    void testHashOfNull() {
        final IllegalArgumentException e = assertThrowsExactly(IllegalArgumentException.class, () -> hashFunction.hash(null));
        assertEquals("key cannot be null", e.getMessage());
    }
}
package com.a9ski.utils.hash;

@FunctionalInterface
public interface HashFunction<K, H> {
    H hash(K key);
}

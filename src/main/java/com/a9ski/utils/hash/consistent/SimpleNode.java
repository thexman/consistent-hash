package com.a9ski.utils.hash.consistent;

import java.util.Objects;

public class SimpleNode<K> implements Node<K> {

    private final K key;

    public SimpleNode(K key) {
        this.key = key;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final SimpleNode<?> that = (SimpleNode<?>) o;

        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}

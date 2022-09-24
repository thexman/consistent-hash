package com.a9ski.utils.hash.consistent;

import java.util.Objects;

public class SimpleVirtualNode<K, N extends Node<K>> extends VirtualNode<K, N> {

    private final K key;

    public SimpleVirtualNode(final N physicalNode, K key) {
        super(physicalNode);
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

        final SimpleVirtualNode<?, ?> that = (SimpleVirtualNode<?, ?>) o;

        return Objects.equals(key, that.key) && Objects.equals(getPhysicalNode(), that.getPhysicalNode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}

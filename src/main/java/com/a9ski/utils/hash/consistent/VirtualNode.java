package com.a9ski.utils.hash.consistent;

public abstract class VirtualNode<K, N extends Node<K>> implements Node<K> {
    private final N physicalNode;

    public VirtualNode(final N physicalNode) {
        if (physicalNode == null) {
            throw new IllegalArgumentException("physicalNode cannot be null");
        }
        this.physicalNode = physicalNode;
    }

    public N getPhysicalNode() {
        return physicalNode;
    }

    public boolean isVirtualNodeOf(Node<K> node) {
        return node != null && node.getKey().equals(this.physicalNode.getKey());
    }
}

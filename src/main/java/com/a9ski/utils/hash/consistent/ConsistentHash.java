package com.a9ski.utils.hash.consistent;

import com.a9ski.utils.hash.HashFunction;
import com.a9ski.utils.hash.consistent.exceptions.KeyAlreadyExists;

import java.util.*;

public class ConsistentHash<K, H extends Comparable<H>, N extends Node<K>> {

    protected final HashFunction<K, H> hashFunction;

    protected final SortedMap<H, N> ring = new TreeMap<>();

    public ConsistentHash(final HashFunction<K, H> hashFunction) {
        if (hashFunction == null) {
            throw new IllegalArgumentException("hashFunction cannot be null");
        }
        this.hashFunction = hashFunction;
    }

    public boolean isEmpty() {
        return ring.isEmpty();
    }

    public void clear() {
        ring.clear();
    }

    public List<Node<K>> getAllNodes() {
        return new ArrayList<>(ring.values());
    }

    public boolean containsNode(K nodeKey) {
        return ring.containsKey(hashFunction.hash(nodeKey));
    }

    public Optional<N> getNodeForKey(K key) {
        if (!ring.isEmpty()) {
            final H keyHash = hashFunction.hash(key);
            final SortedMap<H, N> tail = ring.tailMap(keyHash);
            final H nodeHash = tail.isEmpty() ? ring.firstKey() : tail.firstKey();
            return Optional.of(ring.get(nodeHash));
        } else {
            return Optional.empty();
        }
    }

    public void addNode(N node) throws KeyAlreadyExists {
        if (node != null) {
            final N existingNode = ring.putIfAbsent(hashFunction.hash(node.getKey()), node);
            // compare references and check if the returned reference is different,
            // which means that there is an existing node with the same key.
            if (existingNode != null && existingNode != node) {
                throw new KeyAlreadyExists(String.format("Node with given key '%s' already exists", node.getKey()));
            }
        }
    }

    protected Node getPhysicalNode(Node<K> node) {
        if (node instanceof VirtualNode) {
            return getPhysicalNode(((VirtualNode<K, N>) node).getPhysicalNode());
        } else {
            return node;
        }
    }

    public void addAllNodes(Collection<N> nodes) throws KeyAlreadyExists {
        if (nodes != null) {
            for (final N n : nodes) {
                addNode(n);
            }
        }
    }

    public Node<K> removeNode(K nodeKey) {
        final Node<K> node = ring.remove(hashFunction.hash(nodeKey));
        removeVirtualNodesOf(node);
        return node;
    }

    public boolean removeNode(Node<K> node) {
        if (node != null && ring.remove(hashFunction.hash(node.getKey()), node)) {
            removeVirtualNodesOf(node);
            return true;
        }
        return false;
    }

    protected boolean removeVirtualNodesOf(Node<K> node) {
        if (node != null) {
            // there is a node with given key
            if (isPhysicalNode(node)) {
                // and this is physical node, then remove all virtual nodes of this one
                return ring.entrySet().removeIf(e -> isVirtualNodeOf(e.getValue(), node));
            }
        }
        return false;
    }

    private boolean isPhysicalNode(Node<K> node) {
        return !isVirtualNode(node);
    }

    private boolean isVirtualNode(Node<K> node) {
        return node instanceof VirtualNode;
    }

    protected boolean isVirtualNodeOf(Node<K> node, Node<K> physicalNode) {
        return node instanceof VirtualNode && ((VirtualNode<K, N>) node).isVirtualNodeOf(physicalNode);
    }


}

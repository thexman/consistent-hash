package com.a9ski.utils.hash.consistent;

import com.a9ski.utils.hash.HashFunction;
import com.a9ski.utils.hash.functions.FunctionsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsistentHashTest {

    private final HashFunction<String, Integer> hostIpHash = ip -> Integer.parseInt(ip.substring(ip.lastIndexOf(".")));

    private final ConsistentHash<String, Integer, PhysicalCacheNode> hash = new ConsistentHash<>(hostIpHash);

    @BeforeEach
    void setup() {
        hash.clear();
    }


    @Test
    void isEmpty() {
        assertTrue(new ConsistentHash<>(FunctionsFactory.sha1()).isEmpty());
    }

    @Test
    void getAllNodes() {
        // TODO:
    }

    @Test
    void getNodeForKey() {
        // TODO:
    }

    @Test
    void addNode() {
        // TODO:
        //hash.addNode();
    }

    @Test
    void removeNode() {
        // TODO:
    }

    @Test
    void testRemoveNode() {
        // TODO:
    }

    @Test
    void removeVirtualNodesOf() {
        // TODO:
    }

    @Test
    void isVirtualNodeOf() {
        // TODO:
    }
}
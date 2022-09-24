package com.a9ski.utils.hash.consistent;

import com.a9ski.utils.hash.HashFunction;
import com.a9ski.utils.hash.consistent.exceptions.KeyAlreadyExists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamplesTest {
    @Test
    void exampleDistributedCacheWithMixedPhysicalAndVirtualNodes() throws KeyAlreadyExists {
        final PhysicalCacheNode n1 = new PhysicalCacheNode("192.168.0.10", "10");
        final PhysicalCacheNode n2 = new PhysicalCacheNode("192.168.0.20", "20");
        final PhysicalCacheNode n3 = new PhysicalCacheNode("192.168.0.30", "30");
        final List<PhysicalCacheNode> physicalNodes = List.of(n1, n2, n3);
        final List<VirtualCacheNode> virtualNodes = new ArrayList<>();
        int nodeInd = 0;
        for(int key= 40; key <= 250; key+= 10) {
            virtualNodes.add(new VirtualCacheNode(physicalNodes.get(nodeInd), String.valueOf(key)));
            nodeInd = (nodeInd + 1) % physicalNodes.size();
        }

        final List<CacheNode> allNodes = new ArrayList<>(physicalNodes);
        allNodes.addAll(virtualNodes);

        final HashFunction<String, Integer> hashFunction = x -> Integer.parseInt(x) % 256;

        final ConsistentHash<String, Integer, CacheNode> hash = new ConsistentHash<>(hashFunction);
        hash.addAllNodes(allNodes);

        for(int i = 1; i < 255; i++) {
            final String key = String.format("%03d", i);
            hash.getNodeForKey(key).map(n -> n.getCache().put(key, key));
        }

        for(PhysicalCacheNode node : physicalNodes)  {
            System.out.printf("Node: %s contains keys: %s%n", node.getIp(), node.getCache().keySet());
        }

        assertEquals("[001, 002, 003, 004, 005, 006, 007, 008, 009, 010, 031, 032, 033, 034, 035, 036, 037, 038, 039, 040, 061, 062, 063, 064, 065, 066, 067, 068, 069, 070, 091, 092, 093, 094, 095, 096, 097, 098, 099, 100, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254]", physicalNodes.get(0).getCache().keySet().toString());
        assertEquals("[011, 012, 013, 014, 015, 016, 017, 018, 019, 020, 041, 042, 043, 044, 045, 046, 047, 048, 049, 050, 071, 072, 073, 074, 075, 076, 077, 078, 079, 080, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230]", physicalNodes.get(1).getCache().keySet().toString());
        assertEquals("[021, 022, 023, 024, 025, 026, 027, 028, 029, 030, 051, 052, 053, 054, 055, 056, 057, 058, 059, 060, 081, 082, 083, 084, 085, 086, 087, 088, 089, 090, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240]", physicalNodes.get(2).getCache().keySet().toString());
    }

    @Test
    void exampleDistributedCacheWithOnlyVirtualNodes() throws KeyAlreadyExists {
        final PhysicalCacheNode n1 = new PhysicalCacheNode("192.168.0.10", "10");
        final PhysicalCacheNode n2 = new PhysicalCacheNode("192.168.0.20", "20");
        final PhysicalCacheNode n3 = new PhysicalCacheNode("192.168.0.30", "30");
        final List<PhysicalCacheNode> physicalNodes = List.of(n1, n2, n3);
        final List<CacheNode> virtualNodes = new ArrayList<>();
        int nodeInd = 0;
        virtualNodes.add(new VirtualCacheNode(n1, "10"));
        virtualNodes.add(new VirtualCacheNode(n2, "20"));
        virtualNodes.add(new VirtualCacheNode(n3, "30"));
        for(int key= 40; key <= 250; key+= 10) {
            virtualNodes.add(new VirtualCacheNode(physicalNodes.get(nodeInd), String.valueOf(key)));
            nodeInd = (nodeInd + 1) % physicalNodes.size();
        }

        final HashFunction<String, Integer> hashFunction = x -> Integer.parseInt(x) % 256;

        final ConsistentHash<String, Integer, CacheNode> hash = new ConsistentHash<>(hashFunction);
        hash.addAllNodes(virtualNodes);

        for(int i = 1; i < 255; i++) {
            final String key = String.format("%03d", i);
            hash.getNodeForKey(key).map(n -> n.getCache().put(key, key));
        }

        for(PhysicalCacheNode node : physicalNodes)  {
            System.out.printf("Node: %s contains keys: %s%n", node.getIp(), node.getCache().keySet());
        }

        assertEquals("[001, 002, 003, 004, 005, 006, 007, 008, 009, 010, 031, 032, 033, 034, 035, 036, 037, 038, 039, 040, 061, 062, 063, 064, 065, 066, 067, 068, 069, 070, 091, 092, 093, 094, 095, 096, 097, 098, 099, 100, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254]", physicalNodes.get(0).getCache().keySet().toString());
        assertEquals("[011, 012, 013, 014, 015, 016, 017, 018, 019, 020, 041, 042, 043, 044, 045, 046, 047, 048, 049, 050, 071, 072, 073, 074, 075, 076, 077, 078, 079, 080, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230]", physicalNodes.get(1).getCache().keySet().toString());
        assertEquals("[021, 022, 023, 024, 025, 026, 027, 028, 029, 030, 051, 052, 053, 054, 055, 056, 057, 058, 059, 060, 081, 082, 083, 084, 085, 086, 087, 088, 089, 090, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240]", physicalNodes.get(2).getCache().keySet().toString());
    }
}

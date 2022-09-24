package com.a9ski.utils.hash.consistent;

import java.util.Map;

public class VirtualCacheNode extends VirtualNode<String, PhysicalCacheNode> implements CacheNode {
    private final String key;

    public VirtualCacheNode(PhysicalCacheNode cacheNode, String key) {
        super(cacheNode);
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getIp() {
        return getPhysicalNode().getIp();
    }

    @Override
    public Map<String, Object> getCache() {
        return getPhysicalNode().getCache();
    }
}

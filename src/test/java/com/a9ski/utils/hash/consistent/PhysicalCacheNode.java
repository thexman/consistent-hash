package com.a9ski.utils.hash.consistent;

import java.util.Map;
import java.util.TreeMap;

class PhysicalCacheNode extends SimpleNode<String> implements CacheNode  {
    private final String ip;
    private final Map<String, Object> cache = new TreeMap<>();

    public PhysicalCacheNode(String ip, String key) {
        super(key);
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public Map<String, Object> getCache() {
        return cache;
    }
}

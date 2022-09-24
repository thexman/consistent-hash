package com.a9ski.utils.hash.consistent;

import java.util.Map;

public interface CacheNode extends Node<String> {
    Map<String, Object> getCache();
    String getIp();
}

package com.saurabh.cache.entry;

public class CacheEntry<K,V> {
    private K key;
    private V value;

    public CacheEntry(K key, V value){
        this.key = key;
        this.value = value;
    }
}

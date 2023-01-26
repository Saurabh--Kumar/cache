package com.saurabh.cache;

public class CacheEntry<K,V> {
    K key;
    V value;

    CacheEntry(K key, V value){
        this.key = key;
        this.value = value;
    }
}

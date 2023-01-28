package com.saurabh.cache.entry;

public class NodeEntry<K,V> {
    private final K key;
    private final V value;

    public NodeEntry(K key, V value){
        this.key = key;
        this.value = value;
    }
}

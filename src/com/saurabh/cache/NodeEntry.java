package com.saurabh.cache;

public class NodeEntry<K,V> {
    private final K key;
    private final V value;

    NodeEntry(K key, V value){
        this.key = key;
        this.value = value;
    }
}

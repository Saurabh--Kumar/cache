package com.saurabh.cache;

public interface Cache <K,V>{
    void add(K key, V value);

    V getValue(K key);

    void remove(K key);

    Integer getCurrentSize();

    void printStats();
}

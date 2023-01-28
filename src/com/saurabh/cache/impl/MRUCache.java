package com.saurabh.cache.impl;

import com.saurabh.cache.Cache;
import com.saurabh.cache.entry.ListNode;
import com.saurabh.cache.exception.CacheInitialisationException;

import java.util.HashMap;

/*
 * Cache uses most recently used eviction policy
 * */
public class MRUCache <K,V> extends TimeBasedEvictionCache<K,V> {


    public MRUCache(Integer size) throws CacheInitialisationException {
        super(size);
    }

    @Override
    protected void removeElement() {
        ListNode<K,V> node = start;
        start = start.getNext();
        node.setNext(null);
        if(start != null) {
            start.setPrevious(null);
        }
        valueNodePointerMap.remove(node.getKey());
        currentSize-=1;
    }
}

package com.saurabh.cache.impl;

import com.saurabh.cache.Cache;
import com.saurabh.cache.entry.ListNode;
import com.saurabh.cache.exception.CacheInitialisationException;

import java.util.HashMap;

/*
* Cache uses least recently used eviction policy
* */
public class LRUCache <K,V> extends TimeBasedEvictionCache<K,V> {

    public LRUCache(Integer size) throws CacheInitialisationException {
        super(size);
    }

    @Override
    protected void removeElement() {
        ListNode<K,V> node = end;
        end = end.getPrevious();
        node.setPrevious(null);
        if(end != null) {
            end.setNext(null);
        }
        valueNodePointerMap.remove(node.getKey());
        currentSize-=1;
    }
}

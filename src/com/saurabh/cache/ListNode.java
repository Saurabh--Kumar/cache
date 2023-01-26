package com.saurabh.cache;

public class ListNode <K,V>{
    K key;
    V value;
    ListNode<K,V> previous;
    ListNode<K,V> next;

    ListNode(K key, V value){
        this.key = key;
        this.value = value;
        previous = null;
        next = null;
    }

}

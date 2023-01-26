package com.saurabh.cache;

import java.util.HashMap;

public class LRUCache <K,V>{
    private final Integer maxSize;
    private Integer currentSize;
    private ListNode<K,V> start;
    private ListNode<K,V> end;
    private final HashMap<K, ListNode<K,V>> valueNodePointerMap;

    LRUCache(Integer size) {
        this.maxSize = size;
        this.currentSize = 0;
        start = null;
        end = null;
        valueNodePointerMap = new HashMap<>();
    }


    public void add(K key, V value){
        if(currentSize.equals(maxSize)){
            removeLast();
        }

        ListNode<K,V> node = new ListNode<>(key, value);

        if(currentSize.equals(0)){
            start = node;
            end = node;
        } else {
            node.next = start;
            start.previous = node;
            start = node;
        }
        currentSize+=1;
        valueNodePointerMap.put(key,node);
    }

    public V getValue(K key){
        ListNode<K,V> node = valueNodePointerMap.get(key);

        if(node == null){
            return null;
        }

        if(node.previous == null){ //If Key is at front of list
            return node.value;
        } else if(node.next == null){ // key is at end of list
            end = end.previous;
            node.previous = null;
            end.next = null;
        } else{
            node.next.previous = node.previous;
            node.previous.next = node.next;
            node.previous = null;
            node.next = null;
        }

        node.next = start;
        start.previous = node;
        start = node;
        return node.value;
    }


    public void remove(K key){
        ListNode<K,V> node = valueNodePointerMap.get(key);

        if(node == null){
            return;
        }

        if(node.previous == null){ //removed key if at start
            start = node.next;
            node.next = null;
            if(start != null){
                start.previous = null;
            }
        } else if(node.next == null) { //removed key if at end
            end = node.previous;
            node.previous = null;
            if(end != null){
                end.next = null;
            }
        }
        else {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            node.previous = null;
            node.next = null;
        }
        node = null;
        valueNodePointerMap.remove(key);
        this.currentSize-=1;
    }

    public Integer getCurrentSize(){
        return this.currentSize;
    }

    private void removeLast() {
        ListNode<K,V> node = end;
        end = end.previous;
        node.previous = null;
        if(end != null) {
            end.next = null;
        }
        valueNodePointerMap.remove(node.key);
        currentSize-=1;
    }
}

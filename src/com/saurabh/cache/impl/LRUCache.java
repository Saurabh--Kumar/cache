package com.saurabh.cache.impl;

import com.saurabh.cache.Cache;
import com.saurabh.cache.entry.ListNode;
import com.saurabh.cache.exception.CacheInitialisationException;

import java.util.HashMap;

public class LRUCache <K,V> implements Cache<K,V> {
    private final Integer maxSize;
    private Integer currentSize;
    private ListNode<K,V> start;
    private ListNode<K,V> end;
    private final HashMap<K, ListNode<K,V>> valueNodePointerMap;

    public LRUCache(Integer size) throws CacheInitialisationException {
        if(size <= 0){
            throw new CacheInitialisationException("Cache Size must be greater than 0");
        }
        this.maxSize = size;
        this.currentSize = 0;
        start = null;
        end = null;
        valueNodePointerMap = new HashMap<>();
    }


    @Override
    public void add(K key, V value){
        if(valueNodePointerMap.containsKey(key)){
            updateExistingNode(key, value);
            return ;
        }
        if(currentSize.equals(maxSize)){
            removeLast();
        }

        ListNode<K,V> node = new ListNode<>(key, value);

        if(currentSize.equals(0)){
            start = node;
            end = node;
        } else {
            node.setNext(start);
            start.setPrevious(node);
            start = node;
        }
        currentSize+=1;
        valueNodePointerMap.put(key,node);
    }

    private void updateExistingNode(K key, V value) {
        ListNode<K,V> existingNode = valueNodePointerMap.get(key);
        ListNode<K,V> newNode = new ListNode<>(key, value);

        newNode.setPrevious(existingNode.getPrevious());
        newNode.setNext(existingNode.getNext());
        existingNode.setNext(null);
        existingNode.setPrevious(null);

        if(newNode.getPrevious() == null){         //Node is at start
            start = newNode;
        }

        if(newNode.getNext() == null){         //Node is at end
            end = newNode;
        }

        if(newNode.getNext() != null) {
            newNode.getNext().setPrevious(newNode);
        }
        if(newNode.getPrevious() != null) {
            newNode.getPrevious().setNext(newNode);
        }

        valueNodePointerMap.put(key,newNode);
    }

    @Override
    public V getValue(K key){
        ListNode<K,V> node = valueNodePointerMap.get(key);

        if(node == null){
            return null;
        }

        if(node.getPrevious() == null){ //If Key is at front of list
            return node.getValue();
        } else if(node.getNext() == null){ // key is at end of list
            end = end.getPrevious();
            end.setNext(null);
            node.setPrevious(null);
            node.setNext(null);
        } else{
            node.getNext().setPrevious(node.getPrevious());
            node.getPrevious().setNext(node.getNext());
            node.setPrevious(null);
            node.setNext(null);
        }

        node.setNext(start);
        start.setPrevious(node);
        start = node;
        return node.getValue();
    }

    @Override
    public void remove(K key){
        ListNode<K,V> node = valueNodePointerMap.get(key);

        if(node == null){
            return;
        }

        if(node.getPrevious() == null){ //removed key if at start
            start = node.getNext();
            node.setNext(null);
            if(start != null){
                start.setPrevious(null);
            }
        } else if(node.getNext() == null) { //removed key if at end
            end = node.getPrevious();
            node.setPrevious(null);
            if(end != null){
                end.setNext(null);
            }
        }
        else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
            node.setPrevious(null);
            node.setNext(null);
        }
        node = null;
        valueNodePointerMap.remove(key);
        this.currentSize-=1;
    }

    @Override
    public Integer getCurrentSize(){
        return this.currentSize;
    }

    @Override
    public void printStats() {
        System.out.println("*************************");
        System.out.println(" : Size Stats : ");
        System.out.println("MaxSize : " + this.maxSize + " - " + "CurrentSize : " + this.currentSize);

        System.out.println("*************************");
        System.out.println(" : Elements - Front to back : ");
        ListNode<K,V> ref = this.start;
        while(ref != null){
            System.out.print(ref.getValue() + " - ");
            ref = ref.getNext();
        }

        System.out.println("*************************");
        System.out.println(" : Elements - Back to front : ");
        ref = this.end;
        while(ref != null){
            System.out.print(ref.getValue() + " - ");
            ref = ref.getPrevious();
        }
        System.out.println("*************************");
    }

    private void removeLast() {
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

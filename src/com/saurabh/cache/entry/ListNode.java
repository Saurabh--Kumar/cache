package com.saurabh.cache.entry;

public class ListNode <K,V>{
    private K key;
    private V value;
    ListNode<K,V> previous;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public ListNode<K, V> getPrevious() {
        return previous;
    }

    public void setPrevious(ListNode<K, V> previous) {
        this.previous = previous;
    }

    public ListNode<K, V> getNext() {
        return next;
    }

    public void setNext(ListNode<K, V> next) {
        this.next = next;
    }

    ListNode<K,V> next;

    public ListNode(K key, V value){
        this.key = key;
        this.value = value;
        previous = null;
        next = null;
    }

}

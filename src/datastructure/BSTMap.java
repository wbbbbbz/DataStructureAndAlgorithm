package datastructure;

import datastructure.Map;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private BinarySearchTree<K, V> data;

    public BSTMap() {
        data = new BinarySearchTree<>();
    }

    @Override
    public void add(K key, V value) {
        data.add(key, value);
    }

    @Override
    public V remove(K key) {
        return data.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return data.contains(key);
    }

    @Override
    public V get(K key) {
        return data.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        data.set(key, newValue);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

}
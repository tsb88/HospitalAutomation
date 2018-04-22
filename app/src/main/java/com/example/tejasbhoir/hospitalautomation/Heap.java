package com.example.tejasbhoir.hospitalautomation;

import java.util.ArrayList;
import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> implements Iterable<T> {

    private ArrayList<T> items;

    // Empty constructor using initial capacity constructor
    public Heap() {
        this(10);
    }

    // Constructor to initialize capacity
    public Heap(int capacity) {
        items = new ArrayList<T>(capacity);
    }

    // Constructor to copy items from other heap
    public Heap(Heap<T> in) {
        items = new ArrayList<T>(in.size());
        for(T a: in) {
            items.add(a);
        }
    }

    public void siftUp(int k) {
        while(k>0) {
            int p = (k-1)/2;
            int c = items.get(k).compareTo(items.get(p));
            if(c>0) {
                T temp = items.get(k);
                items.set(k, items.get(p));
                items.set(p, temp);
                k = p;
            } else
                break;
        }
    }

    public void siftDown(int k) {

        while (2*k+1< items.size()) {
            int minIndex = 2*k+1;
            int rightChild = minIndex+1;
            if(rightChild < items.size()) {
                int c = items.get(rightChild).compareTo(items.get(minIndex));
                if(c>0) {
                    minIndex = rightChild;
                }
            }
            int c= items.get(minIndex).compareTo(items.get(k));
            if(c>0) {
                T temp = items.get(minIndex);
                items.set(minIndex, items.get(k));
                items.set(k, temp);
                k = minIndex;
            } else
                break;
        }
    }

    public void insert(T item) {
        items.add(item);
        siftUp(items.size()-1);
    }

    public T deleteMin() throws NoSuchElementException {
        if(items.isEmpty()) {
            throw new NoSuchElementException();
        }

        if(items.size() == 1){
            return items.remove(0);
        }

        T minItem = items.get(0);
        items.set(0, items.remove(items.size()-1));
        siftDown(0);
        return minItem;
    }

    public T getMin() throws NoSuchElementException {
        if(items.isEmpty())
            throw new NoSuchElementException();
        return items.get(0);
    }

    public void merge(Heap<T> hp) {
        for(int i=0; i<hp.items.size();i++) {
            items.add(hp.items.get(i));
        }

        // bottom up build
        int start = items.size()/2-1;
        for(int k=start;k>=0;k--) {
            siftDown(k);
        }
    }

    public int size() {
        return items.size();
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Iterator<T> iterator() {
        return items.iterator();
    }
    public String toString() {
        String ret = "";
        for(T item: items) {
            ret += " " + item;
        }
        return ret;
    }

}

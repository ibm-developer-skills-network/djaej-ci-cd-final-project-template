package com.example.counterservice.model;

/**
 * Counter Model
 */
public class Counter {
    
    private String name;
    private int counter;
    
    public Counter() {
    }
    
    public Counter(String name) {
        this.name = name;
        this.counter = 0;
    }
    
    public Counter(String name, int counter) {
        this.name = name;
        this.counter = counter;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getCounter() {
        return counter;
    }
    
    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    public void increment() {
        this.counter += 1;
    }
}
package com.example.counterservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CounterTest {

    @Test
    void testCreateCounter() {
        Counter counter = new Counter("test");
        assertEquals("test", counter.getName());
        assertEquals(0, counter.getCounter());
    }
    
    @Test
    void testCreateCounterWithValue() {
        Counter counter = new Counter("test", 5);
        assertEquals("test", counter.getName());
        assertEquals(5, counter.getCounter());
    }
    
    @Test
    void testSetName() {
        Counter counter = new Counter("old-name");
        counter.setName("new-name");
        assertEquals("new-name", counter.getName());
    }
    
    @Test
    void testSetCounter() {
        Counter counter = new Counter("test");
        counter.setCounter(10);
        assertEquals(10, counter.getCounter());
    }
    
    @Test
    void testIncrement() {
        Counter counter = new Counter("test");
        assertEquals(0, counter.getCounter());
        
        counter.increment();
        assertEquals(1, counter.getCounter());
        
        counter.increment();
        assertEquals(2, counter.getCounter());
    }
    
    @Test
    void testDefaultConstructor() {
        Counter counter = new Counter();
        assertNull(counter.getName());
        assertEquals(0, counter.getCounter());
    }
}
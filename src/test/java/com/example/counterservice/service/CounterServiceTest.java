package com.example.counterservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.counterservice.model.Counter;
import com.example.counterservice.exception.ResourceNotFoundException;
import com.example.counterservice.exception.ResourceExistsException;

/**
 * Counter Service Tests
 */
@SpringBootTest
public class CounterServiceTest {
    
    @Autowired
    private CounterService counterService;
    
    @BeforeEach
    void setUp() {
        counterService.resetCounters();
    }
    
    @Test
    void testListCounters() {
        // Initial list should be empty
        List<Counter> counters = counterService.listCounters();
        assertEquals(0, counters.size());
        
        // Add a counter and check list size
        counterService.createCounter("test");
        counters = counterService.listCounters();
        assertEquals(1, counters.size());
    }
    
    @Test
    void testCreateCounter() {
        String name = "test-counter";
        Counter counter = counterService.createCounter(name);
        
        assertNotNull(counter);
        assertEquals(name, counter.getName());
        assertEquals(0, counter.getCounter());
    }
    
    @Test
    void testCreateDuplicateCounter() {
        String name = "test-counter";
        counterService.createCounter(name);
        
        assertThrows(ResourceExistsException.class, () -> {
            counterService.createCounter(name);
        });
    }
    
    @Test
    void testGetCounter() {
        String name = "test-counter";
        counterService.createCounter(name);
        
        Counter counter = counterService.getCounter(name);
        assertNotNull(counter);
        assertEquals(name, counter.getName());
        assertEquals(0, counter.getCounter());
    }
    
    @Test
    void testGetNonExistentCounter() {
        assertThrows(ResourceNotFoundException.class, () -> {
            counterService.getCounter("non-existent");
        });
    }
    
    @Test
    void testUpdateCounter() {
        String name = "test-counter";
        counterService.createCounter(name);
        
        Counter counter = counterService.updateCounter(name);
        assertEquals(1, counter.getCounter());
        
        counter = counterService.updateCounter(name);
        assertEquals(2, counter.getCounter());
    }
    
    @Test
    void testUpdateNonExistentCounter() {
        assertThrows(ResourceNotFoundException.class, () -> {
            counterService.updateCounter("non-existent");
        });
    }
    
    @Test
    void testDeleteCounter() {
        String name = "test-counter";
        counterService.createCounter(name);
        
        // Verify counter exists
        assertNotNull(counterService.getCounter(name));
        
        // Delete the counter
        counterService.deleteCounter(name);
        
        // Verify counter no longer exists
        assertThrows(ResourceNotFoundException.class, () -> {
            counterService.getCounter(name);
        });
        
        // Delete non-existent counter should not throw exception
        counterService.deleteCounter(name);
    }
}
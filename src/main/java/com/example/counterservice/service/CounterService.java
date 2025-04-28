package com.example.counterservice.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.counterservice.model.Counter;
import com.example.counterservice.exception.ResourceNotFoundException;
import com.example.counterservice.exception.ResourceExistsException;

/**
 * Counter Service implementation
 */
@Service
public class CounterService {
    
    private final Map<String, Counter> counters = new ConcurrentHashMap<>();
    
    /**
     * Returns all counters
     * @return a list of all counters
     */
    public List<Counter> listCounters() {
        return counters.values().stream().collect(Collectors.toList());
    }
    
    /**
     * Creates a new counter with the given name
     * @param name - the name of the counter
     * @return the created counter
     * @throws ResourceExistsException if counter already exists
     */
    public Counter createCounter(String name) {
        if (counters.containsKey(name)) {
            throw new ResourceExistsException("Counter " + name + " already exists");
        }
        
        Counter counter = new Counter(name);
        counters.put(name, counter);
        return counter;
    }
    
    /**
     * Finds a counter by name
     * @param name - the name of the counter to find
     * @return the found counter
     * @throws ResourceNotFoundException if counter is not found
     */
    public Counter getCounter(String name) {
        Counter counter = counters.get(name);
        if (counter == null) {
            throw new ResourceNotFoundException("Counter " + name + " does not exist");
        }
        return counter;
    }
    
    /**
     * Updates a counter by incrementing its value
     * @param name - the name of the counter to update
     * @return the updated counter
     * @throws ResourceNotFoundException if counter is not found
     */
    public Counter updateCounter(String name) {
        Counter counter = getCounter(name);
        counter.increment();
        return counter;
    }
    
    /**
     * Deletes a counter by name
     * @param name - the name of the counter to delete
     */
    public void deleteCounter(String name) {
        counters.remove(name);
    }
    
    /**
     * Resets all counters (for testing)
     */
    public void resetCounters() {
        counters.clear();
    }
}
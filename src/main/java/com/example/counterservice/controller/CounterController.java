package com.example.counterservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.counterservice.model.Counter;
import com.example.counterservice.service.CounterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for Counter endpoints
 */
@RestController
public class CounterController {
    
    private static final Logger logger = LoggerFactory.getLogger(CounterController.class);
    
    @Autowired
    private CounterService counterService;
    
    /**
     * Index endpoint
     * @return basic information about the service
     */
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> index() {
        logger.info("Request for Base URL");
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Hit Counter Service");
        response.put("version", "1.0.0");
        response.put("url", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/counters").toUriString());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * Health endpoint
     * @return health status
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "OK");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
    
    /**
     * List all counters
     * @return a list of all counters
     */
    @GetMapping("/counters")
    public ResponseEntity<List<Counter>> listCounters() {
        logger.info("Request to list all counters...");
        return new ResponseEntity<>(counterService.listCounters(), HttpStatus.OK);
    }
    
    /**
     * Create a new counter
     * @param name - the name of the counter
     * @return the created counter
     */
    @PostMapping("/counters/{name}")
    public ResponseEntity<Counter> createCounter(@PathVariable String name) {
        logger.info("Request to Create counter: {}...", name);
        
        Counter counter = counterService.createCounter(name);
        
        return ResponseEntity
            .created(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/counters/{name}")
                .buildAndExpand(name)
                .toUri())
            .body(counter);
    }
    
    /**
     * Get a counter by name
     * @param name - the name of the counter
     * @return the counter
     */
    @GetMapping("/counters/{name}")
    public ResponseEntity<Counter> getCounter(@PathVariable String name) {
        logger.info("Request to Read counter: {}...", name);
        return new ResponseEntity<>(counterService.getCounter(name), HttpStatus.OK);
    }
    
    /**
     * Update a counter (increment its value)
     * @param name - the name of the counter
     * @return the updated counter
     */
    @PutMapping("/counters/{name}")
    public ResponseEntity<Counter> updateCounter(@PathVariable String name) {
        logger.info("Request to Update counter: {}...", name);
        return new ResponseEntity<>(counterService.updateCounter(name), HttpStatus.OK);
    }
    
    /**
     * Delete a counter
     * @param name - the name of the counter
     * @return empty response with 204 status
     */
    @DeleteMapping("/counters/{name}")
    public ResponseEntity<Void> deleteCounter(@PathVariable String name) {
        logger.info("Request to Delete counter: {}...", name);
        counterService.deleteCounter(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
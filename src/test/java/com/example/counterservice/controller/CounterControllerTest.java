package com.example.counterservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.counterservice.service.CounterService;

/**
 * Counter Controller Tests
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CounterControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private CounterService counterService;
    
    @BeforeEach
    void setUp() {
        counterService.resetCounters();
    }
    
    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(200)))
            .andExpect(jsonPath("$.message", is("Hit Counter Service")))
            .andExpect(jsonPath("$.version", is("1.0.0")));
    }
    
    @Test
    void testHealth() throws Exception {
        mockMvc.perform(get("/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is("OK")));
    }
    
    @Test
    void testCreateCounter() throws Exception {
        String name = "foo";
        mockMvc.perform(post("/counters/{name}", name))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.counter", is(0)));
    }
    
    @Test
    void testCreateDuplicateCounter() throws Exception {
        String name = "foo";
        mockMvc.perform(post("/counters/{name}", name))
            .andExpect(status().isCreated());
        
        mockMvc.perform(post("/counters/{name}", name))
            .andExpect(status().isConflict());
    }
    
    @Test
    void testListCounters() throws Exception {
        mockMvc.perform(get("/counters"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", is(0)));
        
        // Create a counter and verify it appears in the list
        mockMvc.perform(post("/counters/foo"))
            .andExpect(status().isCreated());
        
        mockMvc.perform(get("/counters"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", is(1)));
    }
    
    @Test
    void testReadCounter() throws Exception {
        String name = "foo";
        mockMvc.perform(post("/counters/{name}", name))
            .andExpect(status().isCreated());
        
        mockMvc.perform(get("/counters/{name}", name))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.counter", is(0)));
    }
    
    @Test
    void testReadMissingCounter() throws Exception {
        mockMvc.perform(get("/counters/nonexistent"))
            .andExpect(status().isNotFound());
    }
    
    @Test
    void testUpdateCounter() throws Exception {
        String name = "foo";
        mockMvc.perform(post("/counters/{name}", name))
            .andExpect(status().isCreated());
        
        mockMvc.perform(get("/counters/{name}", name))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.counter", is(0)));
        
        mockMvc.perform(put("/counters/{name}", name))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.counter", is(1)));
    }
    
    @Test
    void testUpdateMissingCounter() throws Exception {
        mockMvc.perform(put("/counters/nonexistent"))
            .andExpect(status().isNotFound());
    }
    
    @Test
    void testDeleteCounter() throws Exception {
        String name = "foo";
        mockMvc.perform(post("/counters/{name}", name))
            .andExpect(status().isCreated());
        
        mockMvc.perform(delete("/counters/{name}", name))
            .andExpect(status().isNoContent());
        
        mockMvc.perform(get("/counters/{name}", name))
            .andExpect(status().isNotFound());
        
        // Delete again should still return 204
        mockMvc.perform(delete("/counters/{name}", name))
            .andExpect(status().isNoContent());
    }
}
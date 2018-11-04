/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.optimisticLockingExperiment;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Chung2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExperimentControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testPrintCustomer() throws Exception{
        
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(CoreMatchers.containsString("home")));
        
    }        
    
}

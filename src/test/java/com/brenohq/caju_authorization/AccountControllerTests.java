package com.brenohq.caju_authorization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void accountsReturnsLoadedData() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].accountId").value("1"))
                .andExpect(jsonPath("$[0].foodBalance").value(10))
                .andExpect(jsonPath("$[0].mealBalance").value(10))
                .andExpect(jsonPath("$[0].cashBalance").value(10))
                .andExpect(jsonPath("$[1].accountId").value("2"))
                .andExpect(jsonPath("$[1].foodBalance").value(20))
                .andExpect(jsonPath("$[1].mealBalance").value(20))
                .andExpect(jsonPath("$[1].cashBalance").value(20));
    }
}

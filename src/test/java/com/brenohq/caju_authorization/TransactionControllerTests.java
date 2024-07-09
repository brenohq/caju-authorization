package com.brenohq.caju_authorization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void transactionsReturnsLoadedData() throws Exception {
        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].account.accountId").value("1"))
                .andExpect(jsonPath("$[0].amount").value(100.00))
                .andExpect(jsonPath("$[0].mcc").value("5432"))
                .andExpect(jsonPath("$[0].merchant").value("UBER"))
                .andExpect(jsonPath("$[1].account.accountId").value("2"))
                .andExpect(jsonPath("$[1].amount").value(50.00))
                .andExpect(jsonPath("$[1].mcc").value("5431"))
                .andExpect(jsonPath("$[1].merchant").value("IFOOD"));
    }
}

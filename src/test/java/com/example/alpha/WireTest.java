package com.example.alpha;

import com.example.alpha.config.ExchangeConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(classes = {AlphaApplication.class, ExchangeConfig.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(port = 10001, stubs = "classpath:/stubs")
@AutoConfigureMockMvc
public class WireTest {

    @Autowired
    ExchangeConfig exchangeConfig;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getGifTest() throws Exception {
        mockMvc.perform(get("/api/getGif"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("1ICfgVGUI7uP3qrsjr"))
                .andExpect(jsonPath("$.data.url").value("https://giphy.com/gifs/sadienovello-make-it-rain-making-raining-money-1ICfgVGUI7uP3qrsjr"));
    }

    @Test
    public void getLatestTest() throws Exception {
        mockMvc.perform(get("/api/getLatest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp").value(1653466312))
                .andExpect(jsonPath("$.base").value(exchangeConfig.getCurrency()));
    }

    // The test will always fail because getYesterday.json have static date
    @Test
    public void getYesterdayTest() throws Exception {
        mockMvc.perform(get("/api/getYesterday"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp").value(1653379912))
                .andExpect(jsonPath("$.base").value(exchangeConfig.getCurrency()));
    }
}

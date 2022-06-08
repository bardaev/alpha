package com.example.alpha;

import com.example.alpha.controller.RestController;
import com.example.alpha.model.Data;
import com.example.alpha.model.ExchangeResponse;
import com.example.alpha.model.GiphyResponse;
import com.example.alpha.model.Meta;
import com.example.alpha.service.ExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestControllerTest {

    // Excepted response

    // Data
    String RS_ID = "1ICfgVGUI7uP3qrsjr";
    String RS_TYPE = "gif";
    String RS_URL = "https://giphy.com/gifs/sadienovello-make-it-rain-making-raining-money-1ICfgVGUI7uP3qrsjr";
    String RS_TITLE = "Happy Make It Rain GIF by Sadie";

    //Meta
    String RS_MSG = "OK";
    int RS_STATUS = 200;
    String RS_RESPONSE_ID = "aa9b64498a8bd23f75f9ec8d44f53a3a58180ca8";

    //Exchange
    String RS_DISCLAIMER = "Usage subject to terms: https://openexchangerates.org/terms";
    String RS_LICENSE = "https://openexchangerates.org/license";
    int RS_TIMESTAMP = 1654624789;
    String RS_BASE = "RUB";
    double RS_MAP_COUNT = 62.124999;

    @Mock
    ExchangeService exchangeService;

    @InjectMocks
    RestController restController;

    ExchangeResponse exchangeResponse;
    GiphyResponse giphyResponse;
    Meta meta;
    Data data;

    @BeforeEach
    public void setUp() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("RUB", 62.124999);

        exchangeResponse = ExchangeResponse.builder()
                .disclaimer("Usage subject to terms: https://openexchangerates.org/terms")
                .license("https://openexchangerates.org/license")
                .timestamp(1654624789)
                .base("RUB")
                .build();

        exchangeResponse.setRates(rates);

        meta = Meta.builder()
                .msg("OK")
                .status(200)
                .response_id("aa9b64498a8bd23f75f9ec8d44f53a3a58180ca8")
                .build();

        data = Data.builder()
                .id("1ICfgVGUI7uP3qrsjr")
                .type("gif")
                .url("https://giphy.com/gifs/sadienovello-make-it-rain-making-raining-money-1ICfgVGUI7uP3qrsjr")
                .title("Happy Make It Rain GIF by Sadie")
                .build();

        giphyResponse = GiphyResponse.builder()
                .data(data)
                .meta(meta)
                .build();
    }

    @Test
    public void getGifTest() {
        when(exchangeService.getGif()).thenReturn(giphyResponse);

        GiphyResponse giphyRs = restController.getGif();

        assertEquals(RS_ID, giphyRs.getData().getId());
        assertEquals(RS_TYPE, giphyRs.getData().getType());
        assertEquals(RS_URL, giphyRs.getData().getUrl());
        assertEquals(RS_TITLE, giphyRs.getData().getTitle());

        assertEquals(RS_MSG, giphyRs.getMeta().getMsg());
        assertEquals(RS_STATUS, giphyRs.getMeta().getStatus());
        assertEquals(RS_RESPONSE_ID, giphyRs.getMeta().getResponse_id());
    }

    @Test
    public void getLatestTest() {
        when(exchangeService.getLatest()).thenReturn(exchangeResponse);

        ExchangeResponse exchangeRs = restController.getExchangeLatest();

        assertEquals(exchangeRs.getDisclaimer(), RS_DISCLAIMER);
        assertEquals(exchangeRs.getLicense(), RS_LICENSE);
        assertEquals(exchangeRs.getTimestamp(), RS_TIMESTAMP);
        assertEquals(exchangeRs.getBase(), RS_BASE);
        assertEquals(exchangeRs.getRates().get("RUB"), RS_MAP_COUNT);
    }

    @Test
    public void getYesterdayTest() {
        when(exchangeService.getYesterday()).thenReturn(exchangeResponse);

        ExchangeResponse exchangeRs = restController.getExchangeYesterday();

        assertEquals(exchangeRs.getDisclaimer(), RS_DISCLAIMER);
        assertEquals(exchangeRs.getLicense(), RS_LICENSE);
        assertEquals(exchangeRs.getTimestamp(), RS_TIMESTAMP);
        assertEquals(exchangeRs.getBase(), RS_BASE);
        assertEquals(exchangeRs.getRates().get("RUB"), RS_MAP_COUNT);
    }
}

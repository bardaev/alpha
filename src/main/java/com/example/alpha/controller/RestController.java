package com.example.alpha.controller;

import com.example.alpha.model.ExchangeResponse;
import com.example.alpha.model.GiphyResponse;
import com.example.alpha.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/api/getGif")
    public GiphyResponse getGif() {
        return exchangeService.getGif();
    }

    @GetMapping("/api/getLatest")
    public ExchangeResponse getExchangeLatest() {
        return exchangeService.getLatest();
    }

    @GetMapping("/api/getYesterday")
    public ExchangeResponse getExchangeYesterday() {
        return exchangeService.getYesterday();
    }
}

package com.example.alpha.service;

import com.example.alpha.model.ExchangeResponse;
import com.example.alpha.model.GiphyResponse;

public interface ExchangeService {
    GiphyResponse getGif();
    ExchangeResponse getLatest();
    ExchangeResponse getYesterday();
}

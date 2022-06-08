package com.example.alpha.service;

import com.example.alpha.config.ExchangeConfig;
import com.example.alpha.config.GiphyConfig;
import com.example.alpha.model.ExchangeResponse;
import com.example.alpha.model.GiphyResponse;
import com.example.alpha.request.ExchangeRequest;
import com.example.alpha.request.GiphyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeRequest exchangeRequest;

    @Autowired
    private GiphyRequest giphyRequest;

    @Autowired
    private ExchangeConfig exchangeConfig;

    @Autowired
    private GiphyConfig giphyConfig;

    private final String RICH_TAG = "rich";
    private final String BROKE_TAG = "broke";

    @Override
    public GiphyResponse getGif() {

        ExchangeResponse today = getLatest();
        ExchangeResponse yesterday = getYesterday();

        Double rateToday = today.getRates().get(exchangeConfig.getCurrency());
        Double rateYesterday = yesterday.getRates().get(exchangeConfig.getCurrency());

        GiphyResponse rich = giphyRequest.getGif(giphyConfig.getApiKey(), RICH_TAG);
        GiphyResponse broke = giphyRequest.getGif(giphyConfig.getApiKey(), BROKE_TAG);

        return rateToday >= rateYesterday ? rich : broke;
    }

    public ExchangeResponse getLatest() {
        return exchangeRequest.getLatestExchange(exchangeConfig.getApiKey(), exchangeConfig.getCurrency());
    }

    public ExchangeResponse getYesterday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.now();
        date = date.minusDays(1);

        String formatDate = formatter.format(date);

        return exchangeRequest.getYesterdayExchange(exchangeConfig.getApiKey(), exchangeConfig.getCurrency(), formatDate);
    }
}

package com.example.alpha.service;

import com.example.alpha.config.GiphyConfig;
import com.example.alpha.model.GiphyResponse;
import com.example.alpha.request.GiphyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiphyServiceImpl implements GiphyService {

    @Autowired
    private GiphyRequest giphyRequest;

    @Autowired
    private GiphyConfig giphyConfig;

    @Override
    public GiphyResponse getGif(String tag) {
        return giphyRequest.getGif(giphyConfig.getApiKey(), tag);
    }
}

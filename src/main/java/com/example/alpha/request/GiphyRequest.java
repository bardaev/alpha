package com.example.alpha.request;

import com.example.alpha.model.GiphyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphy.url}")
public interface GiphyRequest {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/gifs/random")
    GiphyResponse getGif(
            @RequestParam(name = "api_key") String apiKey,
            @RequestParam(name = "tag") String tag);
}

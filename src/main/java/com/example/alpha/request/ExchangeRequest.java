package com.example.alpha.request;

import com.example.alpha.model.ExchangeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ex", url = "${exchange.url}", path = "/api")
public interface ExchangeRequest {

    @RequestMapping(method = RequestMethod.GET, path = "/latest.json")
    ExchangeResponse getLatestExchange(
            @RequestParam(name = "app_id") String apiKey,
            @RequestParam(name = "symbols") String symbols);

    @RequestMapping(method = RequestMethod.GET, path = "/historical/{date}.json")
    ExchangeResponse getYesterdayExchange(
            @RequestParam(name = "app_id") String apiKey,
            @RequestParam(name = "symbols") String symbols,
            @PathVariable String date);
}

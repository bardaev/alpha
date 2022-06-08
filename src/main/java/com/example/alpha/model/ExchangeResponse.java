package com.example.alpha.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ExchangeResponse {

    String disclaimer;
    String license;
    int timestamp;
    String base;
    Map<String, Double> rates = new HashMap<>();

}

package com.example.alpha.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    String id;
    String type;
    String url;
    String title;
}

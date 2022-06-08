package com.example.alpha.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyResponse {
    com.example.alpha.model.Data data;
    Meta meta;
}

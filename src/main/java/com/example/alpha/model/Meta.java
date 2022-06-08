package com.example.alpha.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Meta {
    String msg;
    int status;
    String response_id;
}

package com.example.mono;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MomoResponse {
    private String partnerCode;
    private String orderId;
    private String requestId;
    private double amount;
    private long responseTime;
    private String message;
    private String orderInfo;
    private int resultCode;
    private String payUrl;
}

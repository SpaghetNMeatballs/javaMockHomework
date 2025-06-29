package com.example.newMock.model;

import lombok.Data;

@Data
public class ResponseDTO {
    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private String balance;
    private String maxLimit;
}

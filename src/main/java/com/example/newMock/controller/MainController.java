package com.example.newMock.controller;

import com.example.newMock.model.RequestDTO;
import com.example.newMock.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Random;


@RestController
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO request) {
        try {
            String clientId = request.getClientId();
            String currency;
            BigDecimal maxLimit;
            char firstChar = clientId.charAt(0);
            if (firstChar == '8') {
                currency = "US";
                maxLimit = new BigDecimal(2000);
            } else if (firstChar == '9') {
                currency = "EU";
                maxLimit = new BigDecimal(1000);
            } else {
                currency = "RUB";
                maxLimit = new BigDecimal(10000);
            }
            ResponseDTO response = new ResponseDTO();

            response.setRqUID(request.getRqUID());
            response.setClientId(clientId);
            response.setAccount(request.getAccount());
            response.setAccount(request.getAccount());
            response.setCurrency(currency);
            int balance = (new Random()).nextInt(maxLimit.intValue());
            response.setBalance(Integer.toString(balance));
            response.setMaxLimit(maxLimit.toString());
            log.info("\n********** Request **********\n{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request));
            log.info("\n********** Response **********\n{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

package com.enigma.mapay.transactionApi.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequest {
    private String commands;
    private String username;
    private String sign;
    private String status;
}

package com.example.ProjectXpress.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class AirtimeRechargeResponse {
    private String requestId;
    private String referenceId;
    private String responseCode;
    private String responseMessage;
    private Object data;
}

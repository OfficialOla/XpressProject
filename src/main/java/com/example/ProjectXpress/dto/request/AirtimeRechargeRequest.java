package com.example.ProjectXpress.dto.request;

import com.example.ProjectXpress.entity.AirtimeDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AirtimeRechargeRequest {
    private String requestId;
    private String uniqueCode;
    private AirtimeDetails details;
}

package com.example.ProjectXpress.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private String requestId;
    private String referenceId;
    private String responseCode;
    private String responseMessage;
    private String data;
}

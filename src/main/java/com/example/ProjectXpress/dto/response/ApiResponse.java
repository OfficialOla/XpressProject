package com.example.ProjectXpress.dto.response;

import lombok.Data;

@Data
public class ApiResponse {
    private String responseCode;
    private String responseMessage;
    private CategoryDto.ProductResponseData data;
}

package com.example.ProjectXpress.controllers.productController;

import com.example.ProjectXpress.dto.request.ProductIdRequest;
import com.example.ProjectXpress.dto.response.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {
    private final String API_URL = "https://billerstest.xpresspayments.com:9603/api/v1/products";
    private final String PUBLIC_KEY = "RPr3J5ml3HDCKaxkZQHfLIMXBFlrGaTE_CVASPUB";

    @PostMapping()
    public ResponseEntity<ApiResponse> getProducts(@RequestBody ProductIdRequest productRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + PUBLIC_KEY);

        HttpEntity<ProductIdRequest> requestEntity = new HttpEntity<>(productRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ApiResponse> responseEntity = restTemplate.postForEntity(API_URL, requestEntity, ApiResponse.class);
        ApiResponse apiResponse = responseEntity.getBody();

        return ResponseEntity.ok(apiResponse);
    }
}

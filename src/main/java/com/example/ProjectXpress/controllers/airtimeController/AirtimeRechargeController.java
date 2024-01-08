package com.example.ProjectXpress.controllers.airtimeController;

import com.example.ProjectXpress.dto.request.AirtimeRechargeRequest;
import com.example.ProjectXpress.dto.response.AirtimeRechargeResponse;
import com.example.ProjectXpress.hash.PaymentHash;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/api/airtime")
@Data
public class AirtimeRechargeController {

    @Value("${api.url}")
    private URI apiUrl;

    @Value("${public.key}")
    private String publicKey;

    @Value("${hmac.key}")
    private String hmacKey;


    @PostMapping("/fulfil")
    public ResponseEntity<AirtimeRechargeResponse> rechargeAirtime(@RequestBody AirtimeRechargeRequest airtimeRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + publicKey);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(airtimeRequest);

            String paymentHash = PaymentHash.calculateHMAC512(jsonRequest, hmacKey);
            headers.set("PaymentHash", paymentHash);

            HttpEntity<AirtimeRechargeRequest> requestEntity = new HttpEntity<>(airtimeRequest, headers);

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForEntity(apiUrl, requestEntity, AirtimeRechargeResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error converting request to JSON");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during airtime recharge");
        }
    }
}

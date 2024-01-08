package com.example.ProjectXpress;

import com.example.ProjectXpress.controllers.airtimeController.AirtimeRechargeController;
import com.example.ProjectXpress.controllers.userController.UserController;
import com.example.ProjectXpress.dto.UserDTO;
import com.example.ProjectXpress.dto.request.AirtimeRechargeRequest;
import com.example.ProjectXpress.dto.request.SignUpRequest;
import com.example.ProjectXpress.dto.response.AirtimeRechargeResponse;
import com.example.ProjectXpress.entity.AirtimeDetails;
import com.example.ProjectXpress.security.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class AirtimeRechargeControllerTest {
    @InjectMocks
    private AirtimeRechargeController airtimeRechargeController;

    @InjectMocks
    private UserController userController;

    @Mock
    private AuthService authService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRechargeAirtime_Successful()  {

        AirtimeDetails details = new AirtimeDetails("08033333333", 100);

        AirtimeRechargeRequest request = new AirtimeRechargeRequest("1994567", "MTN_24207", details);
        AirtimeRechargeResponse response = AirtimeRechargeResponse.builder()
                .requestId("123456")
                .referenceId("OLAY50229624010800205279780724")
                .responseCode("00")
                .responseMessage("Successful")
                .data(null)
                .build();

        HttpHeaders mockHeaders = new HttpHeaders();
        mockHeaders.setContentType(MediaType.APPLICATION_JSON);
        when(restTemplate.postForEntity(any(String.class), any(), Mockito.eq(AirtimeRechargeResponse.class)))
                .thenReturn(ResponseEntity.ok(response));

        URI mockApiUrl = URI.create("https://billerstest.xpresspayments.com:9603/api/v1/airtime/fulfil");
        ReflectionTestUtils.setField(airtimeRechargeController, "apiUrl", mockApiUrl);
        ReflectionTestUtils.setField(airtimeRechargeController, "publicKey", "RPr3J5ml3HDCKaxkZQHfLIMXBFlrGaTE_CVASPUB");
        ReflectionTestUtils.setField(airtimeRechargeController, "hmacKey", "9LBfBStd273t0LPd6WdWvEUxvnyCasUj_CVASPRV");

        ResponseEntity<AirtimeRechargeResponse> responseEntity = airtimeRechargeController.rechargeAirtime(request);

        assertEquals("00", responseEntity.getBody().getResponseCode());
        assertNotNull(responseEntity.getBody().getRequestId());
    }
    @Test
    public void testCreateUser_Success() {

        SignUpRequest signUpRequest = new SignUpRequest();
        UserDTO userDTO = new UserDTO();
        when(authService.createUser(signUpRequest)).thenReturn(userDTO);

        ResponseEntity<?> responseEntity = userController.createUser(signUpRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());

    }

    @Test
    public void testCreateUser_Failure() {

        SignUpRequest signUpRequest = new SignUpRequest();
        when(authService.createUser(signUpRequest)).thenReturn(null);

        ResponseEntity<?> responseEntity = userController.createUser(signUpRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User is not created, try again later", responseEntity.getBody());

    }




}
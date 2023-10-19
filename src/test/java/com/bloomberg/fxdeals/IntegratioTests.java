package com.bloomberg.fxdeals;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegratioTests {

    @JsonProperty
    TestDto dto;
    @Autowired
    private RestTemplate restTemplate;
    private String apiPostPath = "http://localhost:8081/deals/api/v1/addDeal";
    private String apiGetPasth = "http://localhost:8081/deals/api/v1/getDeal/";

    /**
     * this tests the successful request to add new Deal to database
     */
    @Test
    @Order(1)
    void testSuccessful() {
        dto = TestDto.builder()
                .id("1")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("JOD")
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            assertTrue(Objects.requireNonNull(responseEntity.getBody()).contains("Request Processed Successfully"));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * this tests the successful request to get fx Deal from database by id
     */
    @Test
    @Order(2)
    void testGetFxDealById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    apiGetPasth + "1",
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );
            assertTrue(Objects.requireNonNull(responseEntity.getBody()).contains("Request Processed Successfully"));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * this tests when trying to add the same deal twice
     */
    @Test
    @Order(3)
    void testDuplicated() {
        dto = TestDto.builder()
                .id("1")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("JOD")
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Already Configured"));
        }
    }

    /**
     * this tests the failed request to get fx Deal from database by id
     */
    @Test
    @Order(4)
    void testGetFxDealByIdFail() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    apiGetPasth + "5",
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("REJECTED"));
        }
    }

    /**
     * this tests the failed request to get fx Deal from database by id non int id
     */
    @Test
    @Order(5)
    void testGetFxDealByIdNonInt() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    apiGetPasth + "amjed",
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("FAILED"));
        }
    }

    /**
     * test sending request without From Currency ISO Code
     */
    @Test
    @Order(6)
    void testNullFromIsoCode() {
        dto = TestDto.builder()
                .id("6")
                .fromCurrencyIsoCode(null)
                .toCurrencyIsoCode("JOD")
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("REJECTED"));
        }
    }

    /**
     * test sending request without To Currency ISO Code
     */
    @Test
    @Order(7)
    void testNullToIsoCode() {
        dto = TestDto.builder()
                .id("7")
                .fromCurrencyIsoCode("JOD")
                .toCurrencyIsoCode(null)
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("REJECTED"));
        }
    }

    /**
     * test sending request without Time Stamp
     */
    @Test
    @Order(8)
    void testNullTimeStamp() {
        dto = TestDto.builder()
                .id("8")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("JOD")
                .dealTimeStamp(null)
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("REJECTED"));
        }
    }

    /**
     * test sending request with From Currency ISO code that does not comply with the pattern
     */
    @Test
    @Order(9)
    void testFromIsoCodePattern() {
        dto = TestDto.builder()
                .id("9")
                .fromCurrencyIsoCode("usd")
                .toCurrencyIsoCode("JOD")
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("REJECTED"));

        }
    }

    /**
     * test sending request with From Currency ISO code that does not comply with the pattern
     */
    @Test
    @Order(10)
    void testToIsoCodePattern() {
        dto = TestDto.builder()
                .id("10")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("jod")
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("REJECTED"));
        }
    }

    /**
     * test sending request with Time Stamp that does not comply with the pattern
     */
    @Test
    @Order(11)
    void testTimeStampPattern() {
        dto = TestDto.builder()
                .id("10")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("JOD")
                .dealTimeStamp("10-10-2010 10:100")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("REJECTED"));
        }
    }

    /**
     * this tests adding currency code for "FROM CURRENCY" that is not an ISO
     */
    @Test
    @Order(12)
    void testFromCurrency() {
        dto = TestDto.builder()
                .id("3")
                .fromCurrencyIsoCode("USB")
                .toCurrencyIsoCode("JOD")
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("not a currency iso code"));
        }
    }

    /**
     * test sending request without Deal Amount
     */
    @Test
    @Order(13)
    void testNullDealAmount() {
        dto = TestDto.builder()
                .id("8")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("JOD")
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount(null)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("REJECTED"));
        }
    }

    /**
     * test sending request without ID
     */
    @Test
    @Order(14)
    void testNullId() {
        dto = TestDto.builder()
                .id(null)
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("JOD")
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Please provide the Deal ID"));
        }
    }

    /**
     * this tests adding currency code for "TO CURRENCY" that is not an ISO
     */
    @Test
    @Order(15)
    void testToCurrency() {
        dto = TestDto.builder()
                .id("4")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("AAA")
                .dealTimeStamp("10-10-2010 10:10")
                .dealAmount("20.0")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TestDto> requestEntity = new HttpEntity<>(dto, headers);
        try {
            restTemplate.exchange(
                    apiPostPath,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("not a currency iso code"));
        }
    }


}

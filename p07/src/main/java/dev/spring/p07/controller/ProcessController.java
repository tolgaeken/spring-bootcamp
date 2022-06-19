package dev.spring.p07.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.spring.p07.entity.ProcessStartTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/processStartTime")
public class ProcessController {
    private final static String PROCESS_ENDPOINT = "http://localhost:8080/actuator/metrics/process.start.time";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public String getProcessTime() throws JsonProcessingException {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(PROCESS_ENDPOINT, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ProcessStartTime processStartTime = objectMapper.readValue(responseEntity.getBody(), ProcessStartTime.class);
        // LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli((long) processStartTime.getMeasurements().get(0).getValue()), ZoneId.systemDefault());
        LocalDateTime date = Instant.ofEpochSecond((long) processStartTime.getMeasurements().get(0).getValue()).atZone(ZoneId.systemDefault()).toLocalDateTime();


        return "Server is running since :" + date;
    }
}

package ru.akylyar.springcourse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.akylyar.springcourse.dto.MeasurementDTO;

import java.util.*;

public class Consumer {

    public void registerSensor(String name, String url) {
        Map<String, Object> jsonToSend = new HashMap<>();
        jsonToSend.put("name", name);

        sendRequest(url, jsonToSend);
    }

    public void sendMeasurements(String url, String sensorName) {
        Random random = new Random();
        Map<String, Object> jsonToSend = new HashMap<>();

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            jsonToSend.put("value", String.valueOf(random.nextDouble(-80, 74)));
            jsonToSend.put("raining", String.valueOf(random.nextBoolean()));
            jsonToSend.put("sensor", sensorName);

            sendRequest(url, jsonToSend);
        }

    }

    public List<MeasurementDTO> getMeasurements(String url) {
        final RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<MeasurementDTO>> responseEntity =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<MeasurementDTO>>() {}
                );

        return responseEntity.getBody();
    }

    public void sendRequest(String url, Map<String, Object> jsonToSend) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonToSend, headers);

        restTemplate.postForObject(url, request, String.class);
    }
}

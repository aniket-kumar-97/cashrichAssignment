package com.cash.assignment.Utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class CoinUtil {
  public static LinkedHashMap coinData(String symbol) {
    RestTemplate restTemplate = new RestTemplate();

    String urlTemplate = UriComponentsBuilder.fromHttpUrl(Constants.COIN_URL)
        .queryParam("symbol", "{symbol}")
        .encode().toUriString();

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(Constants.COIN_HEADER_KEY, Constants.COIN_HEADER_VALUE);
    HttpEntity<String> entity = new HttpEntity<>("body", httpHeaders);

    Map<String, String> params = new HashMap<>();
    params.put("symbol", symbol);

    return (LinkedHashMap) Objects.requireNonNull(
        restTemplate.exchange(urlTemplate, HttpMethod.GET, entity,
            LinkedHashMap.class, params).getBody()).get("data");
  }
}

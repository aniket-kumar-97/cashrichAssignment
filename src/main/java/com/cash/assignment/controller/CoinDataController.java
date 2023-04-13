package com.cash.assignment.controller;

import com.cash.assignment.service.CoinDataService;
import java.util.LinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/coinData")
@Slf4j
public class CoinDataController {

  private final CoinDataService coinDataService;

  public CoinDataController(CoinDataService coinDataService) {
    this.coinDataService = coinDataService;
  }

  @GetMapping("/")
  public ResponseEntity<LinkedHashMap> getCoinData(@RequestParam(name = "symbol") String symbol)
      throws Exception {
    try {
      return new ResponseEntity<>(coinDataService.getCoinData(symbol), HttpStatus.OK);
    } catch (Exception ex) {
      log.error("Error occurred while updating user ", ex);
      throw new Exception(ex.getMessage());
    }
  }
}

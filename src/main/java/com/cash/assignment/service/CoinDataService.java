package com.cash.assignment.service;

import com.cash.assignment.Exception.CoinDataServiceException;
import com.cash.assignment.Utils.CoinUtil;
import com.cash.assignment.model.User;
import com.cash.assignment.repository.UserRepository;
import java.util.LinkedHashMap;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CoinDataService {
  @Autowired
  private UserRepository userRepository;

  public LinkedHashMap getCoinData(String symbol) {
    try {
      LinkedHashMap coinData = CoinUtil.coinData(symbol);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentUserName = authentication.getName();

      Optional<User> user = userRepository.findByUserName(currentUserName);
      int userid;
      if(user.isPresent()) {
        userid = user.get().getId();
      }

      return coinData;
    } catch (Exception e) {
      log.error("Error occurred while calling external Api", e);
      throw new CoinDataServiceException(e.getMessage());
    }
  }
}

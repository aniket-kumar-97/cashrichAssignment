package com.cash.assignment.service;

import com.cash.assignment.model.User;
import com.cash.assignment.repository.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUserName(username);
    if (user.isPresent()) {
      return new org.springframework.security.core.userdetails.User(user.get().getUserName(),
          user.get().getPassword(), new ArrayList<>());
    }
    log.error("user not found with username {}", username);
    return null;
  }
}

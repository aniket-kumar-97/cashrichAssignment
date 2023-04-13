package com.cash.assignment.service;

import com.cash.assignment.DTO.UserSignInApiRequest;
import com.cash.assignment.DTO.UserSignUpApiRequest;
import com.cash.assignment.Exception.UserServiceException;
import com.cash.assignment.Utils.JwtUtil;
import com.cash.assignment.model.User;
import com.cash.assignment.repository.UserRepository;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtUtil jwtUtil;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public int userSignup(UserSignUpApiRequest userSignUpApiRequest) {
    try {
      log.info("Started creating new user..");
      long ms = System.currentTimeMillis();

      Optional<User> userByUserNameFromDb = userRepository.findByUserName(
          userSignUpApiRequest.getUsername());

      if (userByUserNameFromDb.isPresent()) {
        throw new UserServiceException(
            "user already present with given username " + userSignUpApiRequest.getUsername());
      }

      User newUser = userRepository.save(
          User.builder()
              .userName(userSignUpApiRequest.getUsername())
              .password(passwordEncoder.encode(userSignUpApiRequest.getPassword()))
              .createdAt(Instant.now().getEpochSecond())
              .build()
      );

      log.info("Time taken to create new user {} ms", (System.currentTimeMillis() - ms));

      return Objects.nonNull(newUser) ? 1 : 0;

    } catch (Exception e) {
      log.error("Error while creating new user ", e);
      throw new UserServiceException(e.getMessage());
    }
  }

  public Map<String, String> userSignIn(UserSignInApiRequest userSignInApiRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              userSignInApiRequest.getUsername(), userSignInApiRequest.getPassword()
          )
      );

      if (authentication.isAuthenticated()) {
        Map<String, String> response = new HashMap<>();
        response.put("token", jwtUtil.generateToken(userSignInApiRequest.getUsername()));
        return response;
      } else {
        throw new UserServiceException("Incorrect Username/password");
      }
    } catch (Exception e) {
      log.error("Error while user signIn ", e);
      throw new UserServiceException(e.getMessage());
    }
  }

  public User getUserById(int userId) {
    try {
      log.info("Started getting user from DB..");
      long ms = System.currentTimeMillis();

      Optional<User> userById = userRepository.findById(userId);

      log.info("Time taken for getting user by id - {}, {} ms", userId,
          (System.currentTimeMillis() - ms));
      if (userById.isEmpty()) {
        throw new UserServiceException("User does not exists with userId " + userId);
      }

      return userById.get();
    } catch (Exception e) {
      log.error("Error while user by userId ", e);
      throw new UserServiceException(e.getMessage());
    }
  }

  public int updateUser(int userId, Map<String, Object> fields) {
    try {
      log.info("Started updating user with user id {} ..", userId);
      long ms = System.currentTimeMillis();

      Optional<User> userById = userRepository.findById(userId);

      if (userById.isEmpty()) {
        throw new UserServiceException("User does not exists with userId " + userId);
      }
      User existingUser = userById.get();

      fields.forEach((key, value) -> {
        if (!Objects.equals(key, "id")) {
          Field field = ReflectionUtils.findField(User.class, key);
          if (field != null) {
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingUser, value);
          }
        }
      });

      User updatedUser = userRepository.save(existingUser);
      log.info("Time taken for updating user by id - {}, {} ms", userId,
          (System.currentTimeMillis() - ms));

      return Objects.nonNull(updatedUser) ? 1 : 0;

    } catch (Exception e) {
      log.error("ERROR while updating user by userId ", e);
      throw new UserServiceException(e.getMessage());
    }
  }

}

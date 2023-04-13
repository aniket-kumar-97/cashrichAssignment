package com.cash.assignment.controller;

import com.cash.assignment.DTO.UserSignInApiRequest;
import com.cash.assignment.DTO.UserSignUpApiRequest;
import com.cash.assignment.model.User;
import com.cash.assignment.service.UserService;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<Integer> userSignup(
      @RequestBody @Valid final UserSignUpApiRequest userSignUpApiRequest) throws Exception {
    try {
      return new ResponseEntity<>(userService.userSignup(userSignUpApiRequest), HttpStatus.CREATED);
    } catch (Exception ex) {
      log.error("Error occurred while user signup ", ex);
      throw new Exception(ex.getMessage());
    }
  }

  @PostMapping("/signin")
  public ResponseEntity<Map<String, String>> userSignIn(
      @RequestBody @Valid final UserSignInApiRequest userSignInApiRequest)
      throws Exception {
    try {

      return new ResponseEntity<>(userService.userSignIn(userSignInApiRequest), HttpStatus.OK);

    } catch (Exception ex) {
      log.error("Error occurred while user signIn ", ex);
      throw new Exception(ex.getMessage());
    }
  }

  @PatchMapping("/updateUserDetails/{userId}")
  public ResponseEntity<Integer> userUpdate(@PathVariable final int userId,
      @RequestBody final Map<String, Object> fields) throws Exception {
    try {
      return new ResponseEntity<>(userService.updateUser(userId, fields), HttpStatus.OK);
    } catch (Exception ex) {
      log.error("Error occurred while updating user ", ex);
      throw new Exception(ex.getMessage());
    }
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable final int userId) throws Exception {
    try {
      return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    } catch (Exception ex) {
      log.error("Error occurred while retrieving user ", ex);
      throw new Exception(ex.getMessage());
    }
  }

}

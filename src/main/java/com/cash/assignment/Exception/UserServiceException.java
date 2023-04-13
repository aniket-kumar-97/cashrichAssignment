package com.cash.assignment.Exception;

public class UserServiceException extends RuntimeException {

  public UserServiceException() {
    super();
  }

  public UserServiceException(String message) {
    super(message);
  }

  public UserServiceException(Throwable cause) {
    super(cause);
  }
}

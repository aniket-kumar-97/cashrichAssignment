package com.cash.assignment.Exception;

public class CoinDataServiceException extends RuntimeException {

  public CoinDataServiceException() {
    super();
  }

  public CoinDataServiceException(String message) {
    super(message);
  }

  public CoinDataServiceException(Throwable cause) {
    super(cause);
  }

}

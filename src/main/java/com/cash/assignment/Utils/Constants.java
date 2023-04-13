package com.cash.assignment.Utils;

public class Constants {

  public static final String REGEX_UPPERCASE = ".*?[A-Z].*";
  public static final String REGEX_LOWERCASE = ".*?[a-z].*";
  public static final String REGEX_DIGIT = ".*?[0-9].*";
  public static final String REGEX_SPECIAL_CHARACTER = "(.*\\W.*)";

  public static final String COIN_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
  public static final String COIN_HEADER_KEY = "X-CMC_PRO_API_KEY";
  public static final String COIN_HEADER_VALUE = "27ab17d1-215f-49e5-9ca4-afd48810c149";

}

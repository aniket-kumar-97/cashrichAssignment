package com.cash.assignment.DTO;

import com.cash.assignment.Utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInApiRequest {

  @NotNull(message = "username is required")
  @NotBlank(message = "username is cannot be empty")
  @Size(
      min = 4,
      max = 15,
      message = "Username length must be greater than 4 and less than equal to 15"
  )
  private String username;
  @NotNull(message = "password is required")
  @NotBlank(message = "password is cannot be empty")
  @Size(
      min = 8,
      max = 15,
      message = "Password length must be greater than 8 and less than equal to 15"
  )
  @Pattern(regexp = Constants.REGEX_UPPERCASE, message = "Password must contain 1 upperCase letter")
  @Pattern(regexp = Constants.REGEX_LOWERCASE, message = "Password must contain 1 lowerCase letter")
  @Pattern(regexp = Constants.REGEX_DIGIT, message = "Password must contain 1 digit")
  @Pattern(
      regexp = Constants.REGEX_SPECIAL_CHARACTER,
      message = "Password must contain 1 Special character"
  )
  private String password;
}

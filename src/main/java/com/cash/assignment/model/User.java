package com.cash.assignment.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;
  @Column(
      name = "first_name"
  )
  private String firstName;
  @Column(
      name = "last_name"
  )
  private String lastName;
  @Column(
      name = "user_name",
      nullable = false,
      unique = true
  )
  private String userName;
  @Column(
      name = "password",
      nullable = false
  )
  private String password;
  @Column(
      name = "created_at"
  )
  private Long createdAt;
}

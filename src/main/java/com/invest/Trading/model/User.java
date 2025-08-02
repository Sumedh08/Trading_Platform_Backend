package com.invest.Trading.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.invest.Trading.Domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String email;
    private String mobile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean isVerified = false;





    private USER_ROLE role= USER_ROLE.ROLE_CUSTOMER;

}


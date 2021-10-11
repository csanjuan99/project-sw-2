package com.app.medyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUserDTO {


    private int userId;
    private String userName;
    private String userLastname;
    private int userDni;
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userPassword;

}

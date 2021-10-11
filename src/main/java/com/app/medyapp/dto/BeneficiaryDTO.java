package com.app.medyapp.dto;

import com.app.medyapp.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BeneficiaryDTO {
    private User user;
    private int beneficiaryId;
    @NotNull
    private int beneficiaryDni;
    @NotBlank
    private String beneficiaryName;
    @NotBlank
    private String beneficiaryLastname;

}

package com.app.medyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {

    @NotNull
    private long subscriptionId;
    @NotNull
    private long subscriptionType;
    @NotNull
    private Date subscriptionDate;
}

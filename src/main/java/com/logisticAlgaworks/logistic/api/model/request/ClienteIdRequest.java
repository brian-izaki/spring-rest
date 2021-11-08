package com.logisticAlgaworks.logistic.api.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteIdRequest {
    @NotNull
    private Long id;
}

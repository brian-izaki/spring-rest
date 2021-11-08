package com.logisticAlgaworks.logistic.api.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteIdRequest {

    @ApiModelProperty(value = "Id de cliente existente")
    @NotNull
    private Long id;

}

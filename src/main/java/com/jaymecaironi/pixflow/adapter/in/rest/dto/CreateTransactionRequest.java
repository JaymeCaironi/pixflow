package com.jaymecaironi.pixflow.adapter.in.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateTransactionRequest(
    @NotBlank String originKey,
    @NotBlank String destinationKey,
    @NotNull @DecimalMin(value = "0.01") BigDecimal amount
) {
}

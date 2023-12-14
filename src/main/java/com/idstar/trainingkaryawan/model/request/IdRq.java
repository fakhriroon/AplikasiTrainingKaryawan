package com.idstar.trainingkaryawan.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IdRq {
    @NotNull(message = "ID cannot be empty!")
    private Integer id;
}

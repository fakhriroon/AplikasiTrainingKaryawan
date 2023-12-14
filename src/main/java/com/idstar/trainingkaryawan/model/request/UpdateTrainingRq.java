package com.idstar.trainingkaryawan.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTrainingRq {
    @NotNull(message = "ID cannot be empty!")
    private Integer id;

    @NotNull(message = "tema cannot be empty!")
    private String tema;

    @NotNull(message = "pengajar cannot be empty!")
    private String pengajar;
}

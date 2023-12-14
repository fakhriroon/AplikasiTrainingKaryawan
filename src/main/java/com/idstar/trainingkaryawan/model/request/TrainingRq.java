package com.idstar.trainingkaryawan.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainingRq {
    @NotNull(message = "tema cannot be empty!")
    private String tema;

    @NotNull(message = "pengajar cannot be empty!")
    private String pengajar;
}

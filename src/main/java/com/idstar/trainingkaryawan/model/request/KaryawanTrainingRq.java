package com.idstar.trainingkaryawan.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class KaryawanTrainingRq {
    private IdRq karyawan;
    private IdRq training;

    @NotNull(message = "Tanggal cannot be empty!")
    private Date tanggal;
}

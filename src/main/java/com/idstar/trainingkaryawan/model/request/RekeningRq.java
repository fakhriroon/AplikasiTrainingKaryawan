package com.idstar.trainingkaryawan.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RekeningRq {
    @NotNull(message = "Nama cannot be empty!")
    private String nama;

    @NotNull(message = "Jenis cannot be empty!")
    private String jenis;

    @NotNull(message = "Rekening cannot be empty!")
    private String rekening;

    @NotNull(message = "Alamat cannot be empty!")
    private String alamat;

    private IdRq karyawan;
}

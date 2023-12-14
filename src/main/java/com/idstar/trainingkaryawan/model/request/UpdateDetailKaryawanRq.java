package com.idstar.trainingkaryawan.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateDetailKaryawanRq {

    @NotBlank(message = "id tidak boleh kosong")
    private Integer id;

    @NotNull(message = "nik tidak boleh kosong")
    private String nik;

    @NotNull(message = "npwp tidak boleh kosong")
    private String npwp;
}

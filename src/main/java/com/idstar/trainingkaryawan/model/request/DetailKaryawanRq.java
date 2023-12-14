package com.idstar.trainingkaryawan.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class DetailKaryawanRq {

    @NotNull(message = "nik tidak boleh kosong")
    private String nik;

    @NotNull(message = "npwp tidak boleh kosong")
    private String npwp;

}

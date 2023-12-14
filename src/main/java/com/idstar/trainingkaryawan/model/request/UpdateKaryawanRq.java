package com.idstar.trainingkaryawan.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateKaryawanRq {
    @NotNull(message = "ID cannot be empty!")
    private Integer id;

    @NotBlank(message = "Nama cannot be empty!")
    private String nama;

    @NotNull(message = "DOB cannot be empty!")
    private Date dob;

    @NotBlank(message = "Status cannot be empty!")
    private String status;

    @NotBlank(message = "Alamat cannot be empty!")
    private String alamat;

    private UpdateDetailKaryawanRq detailKaryawan;
}

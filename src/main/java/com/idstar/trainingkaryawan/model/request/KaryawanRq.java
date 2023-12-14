package com.idstar.trainingkaryawan.model.request;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
public class KaryawanRq {

    @NotBlank(message = "Nama cannot be empty!")
    private String nama;

    @NotNull(message = "DOB cannot be empty!")
    private Date dob;

    @NotBlank(message = "Status cannot be empty!")
    private String status;

    @NotBlank(message = "Alamat cannot be empty!")
    private String alamat;

    private DetailKaryawanRq detailKaryawan;

}

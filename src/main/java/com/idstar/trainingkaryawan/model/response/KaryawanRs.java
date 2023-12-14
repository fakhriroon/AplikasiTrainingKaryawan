package com.idstar.trainingkaryawan.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class KaryawanRs extends DateRs {
    private Integer id;
    private String nama;
    private String dob;
    private String status;
    private String alamat;
    private DetailKaryawanRs detailKaryawan;
    public KaryawanRs(Date createdDate, Date updatedDate, Date deletedDate, Integer id, String nama, Date dob, String status, String alamat, DetailKaryawanRs detailKaryawan) {
        super(createdDate, updatedDate, deletedDate);
        this.id = id;
        this.nama = nama;
        this.dob = convertDateToString(dob);
        this.status = status;
        this.alamat = alamat;
        this.detailKaryawan = detailKaryawan;
    }
}

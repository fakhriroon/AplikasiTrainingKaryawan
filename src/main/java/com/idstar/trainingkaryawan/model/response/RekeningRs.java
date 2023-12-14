package com.idstar.trainingkaryawan.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class RekeningRs extends DateRs {
    private Integer id;
    private String nama;
    private String jenis;
    private String rekening;
    private String alamat;
    private KaryawanRekeningRs karyawan;

    public RekeningRs(Date createdDate, Date updatedDate, Date deletedDate, Integer id, String nama, String jenis, String rekening, String alamat, KaryawanRekeningRs karyawan) {
        super(createdDate, updatedDate, deletedDate);
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.rekening = rekening;
        this.alamat = alamat;
        this.karyawan = karyawan;
    }
}

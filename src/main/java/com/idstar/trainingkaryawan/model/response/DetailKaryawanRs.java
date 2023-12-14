package com.idstar.trainingkaryawan.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetailKaryawanRs extends DateRs {
    private Integer id;
    private String nik;

    private String npwp;

    public DetailKaryawanRs(Date createdDate, Date updatedDate, Date deletedDate, Integer id, String nik, String npwp) {
        super(createdDate, deletedDate, updatedDate);
        this.id = id;
        this.nik = nik;
        this.npwp = npwp;
    }
}

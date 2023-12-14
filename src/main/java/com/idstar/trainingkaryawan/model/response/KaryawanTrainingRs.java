package com.idstar.trainingkaryawan.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class KaryawanTrainingRs extends DateRs {
    private Integer id;
    private KaryawanRs karyawan;
    private TrainingRs training;
    private Date tanggal;

    public KaryawanTrainingRs(Date createdDate, Date updatedDate, Date deletedDate, Integer id, KaryawanRs karyawan, TrainingRs training, Date tanggal) {
        super(createdDate, updatedDate, deletedDate);
        this.id = id;
        this.karyawan = karyawan;
        this.training = training;
        this.tanggal = tanggal;
    }
}

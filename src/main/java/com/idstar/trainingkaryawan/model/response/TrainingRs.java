package com.idstar.trainingkaryawan.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingRs extends DateRs {
    private Integer id;
    private String tema;
    private String pengajar;

    public TrainingRs(Date createdDate, Date updatedDate, Date deletedDate, Integer id, String tema, String pengajar) {
        super(createdDate, updatedDate, deletedDate);
        this.id = id;
        this.tema = tema;
        this.pengajar = pengajar;
    }
}

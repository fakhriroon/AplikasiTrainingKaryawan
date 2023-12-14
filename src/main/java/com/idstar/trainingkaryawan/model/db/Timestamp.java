package com.idstar.trainingkaryawan.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class Timestamp {

    @Column(name = "sys_created_date")
    private Date createdDate;

    @Column(name = "sys_updated_date")
    private Date updatedDate;

    @Column(name = "sys_deleted_date")
    private Date deletedDate;
}

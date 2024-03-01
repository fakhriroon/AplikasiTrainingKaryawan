package com.idstar.trainingkaryawan.model.db;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detail_karyawan")
public class DetailKaryawan extends Timestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nik")
    private String nik;

    @Column(name = "npwp")
    private String npwp;

    @OneToOne(mappedBy = "detailKaryawan", cascade = CascadeType.ALL)
    private Karyawan karyawan;

    public DetailKaryawan(Date createdDate, Date updatedDate, Date deletedDate, String nik, String npwp) {
        this.setCreatedDate(createdDate);
        this.setUpdatedDate(updatedDate);
        this.setDeletedDate(deletedDate);
        this.nik = nik;
        this.npwp = npwp;
    }
}

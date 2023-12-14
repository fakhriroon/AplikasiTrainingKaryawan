package com.idstar.trainingkaryawan.model.db;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "karyawan_training")
public class KaryawanTraining extends Timestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tanggal")
    private Date tanggal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "karyawan_id", referencedColumnName = "id")
    private Karyawan karyawan;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    private Training training;

    public KaryawanTraining(Date tanggal, Karyawan karyawan, Training training, Date createdDate) {
        this.tanggal = tanggal;
        this.karyawan = karyawan;
        this.training = training;
        this.setCreatedDate(createdDate);
    }
}

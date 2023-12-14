package com.idstar.trainingkaryawan.model.db;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "training")
public class Training extends Timestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pengajar")
    private String pengajar;

    @Column(name = "tema")
    private String tema;

    @ManyToOne
    @JoinColumn(name = "karyawan_id", referencedColumnName = "id")
    private Karyawan karyawan;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<KaryawanTraining> karyawanTrainings = new HashSet<>();

    public Training(String tema, String pengajar, Date createdDate) {
        this.tema = tema;
        this.pengajar = pengajar;
        this.setCreatedDate(createdDate);
    }
}

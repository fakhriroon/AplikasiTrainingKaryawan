package com.idstar.trainingkaryawan.model.db;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "rekening")
public class Rekening extends Timestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "rekening")
    private String rekening;

    @Column(name = "jenis")
    private String jenis;

    @Column(name = "alamat")
    private String alamat;

    @ManyToOne
    @JoinColumn(name = "karyawan_id")
    private Karyawan karyawan;

    public Rekening(String jenis, String nama, String rekening, String alamat, Karyawan karyawan, Date createdDate) {
        this.jenis = jenis;
        this.nama = nama;
        this.rekening = rekening;
        this.alamat = alamat;
        this.karyawan = karyawan;
        this.setCreatedDate(createdDate);
    }
}

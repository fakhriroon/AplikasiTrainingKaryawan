package com.idstar.trainingkaryawan.repository;

import com.idstar.trainingkaryawan.model.db.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, Integer> {
}

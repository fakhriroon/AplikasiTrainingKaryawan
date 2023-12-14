package com.idstar.trainingkaryawan.repository;

import com.idstar.trainingkaryawan.model.db.DetailKaryawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailKaryawanRepository extends JpaRepository<DetailKaryawan, Integer> {
}

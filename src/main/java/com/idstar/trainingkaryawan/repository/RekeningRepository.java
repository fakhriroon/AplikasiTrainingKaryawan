package com.idstar.trainingkaryawan.repository;

import com.idstar.trainingkaryawan.model.db.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeningRepository extends JpaRepository<Rekening, Integer> {
}

package com.idstar.trainingkaryawan.repository;

import com.idstar.trainingkaryawan.model.db.KaryawanTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanTrainingRepository extends JpaRepository<KaryawanTraining, Integer> {
}

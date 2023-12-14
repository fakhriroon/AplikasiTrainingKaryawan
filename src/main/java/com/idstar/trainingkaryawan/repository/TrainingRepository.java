package com.idstar.trainingkaryawan.repository;

import com.idstar.trainingkaryawan.model.db.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
}

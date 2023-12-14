package com.idstar.trainingkaryawan.utility;

import com.idstar.trainingkaryawan.model.db.Karyawan;
import com.idstar.trainingkaryawan.model.db.KaryawanTraining;
import com.idstar.trainingkaryawan.model.db.Rekening;
import com.idstar.trainingkaryawan.model.db.Training;
import com.idstar.trainingkaryawan.repository.KaryawanRepository;
import com.idstar.trainingkaryawan.repository.KaryawanTrainingRepository;
import com.idstar.trainingkaryawan.repository.RekeningRepository;
import com.idstar.trainingkaryawan.repository.TrainingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Slf4j
@Component
public class FindById {

    public Karyawan findKaryawanById(KaryawanRepository karyawanRepository, int id) {
        return karyawanRepository.findById(id).orElseThrow(() -> {
            log.error("Karyawan {} not found!", id);
            throw new NoSuchElementException("Data Karyawan not found!");
        });
    }

    public Training findTrainingById(TrainingRepository trainingRepository, int id) {
        return trainingRepository.findById(id).orElseThrow(() -> {
            log.error("Training {} not found!", id);
            throw new NoSuchElementException("Data Training not found!");
        });
    }

    public KaryawanTraining findKaryawanTrainingById(KaryawanTrainingRepository karyawanTrainingRepository, int id) {
        return karyawanTrainingRepository.findById(id).orElseThrow(() -> {
            log.error("Karyawan Training {} not found!", id);
            throw new NoSuchElementException("Data Karyawan Training not found!");
        });
    }

    public Rekening findRekeningById(RekeningRepository rekeningRepository, int id) {
        return rekeningRepository.findById(id).orElseThrow(() -> {
            log.error("Rekening {} not found!", id);
            throw new NoSuchElementException("Data Rekening not found!");
        });
    }

}

package com.idstar.trainingkaryawan.service;

import com.idstar.trainingkaryawan.model.db.DetailKaryawan;
import com.idstar.trainingkaryawan.model.db.Karyawan;
import com.idstar.trainingkaryawan.model.db.KaryawanTraining;
import com.idstar.trainingkaryawan.model.db.Training;
import com.idstar.trainingkaryawan.model.request.IdRq;
import com.idstar.trainingkaryawan.model.request.KaryawanTrainingRq;
import com.idstar.trainingkaryawan.model.request.UpdateKaryawanTrainingRq;
import com.idstar.trainingkaryawan.model.response.DetailKaryawanRs;
import com.idstar.trainingkaryawan.model.response.KaryawanRs;
import com.idstar.trainingkaryawan.model.response.KaryawanTrainingRs;
import com.idstar.trainingkaryawan.model.response.TrainingRs;
import com.idstar.trainingkaryawan.repository.KaryawanRepository;
import com.idstar.trainingkaryawan.repository.KaryawanTrainingRepository;
import com.idstar.trainingkaryawan.repository.TrainingRepository;
import com.idstar.trainingkaryawan.utility.FindById;
import com.idstar.trainingkaryawan.utility.ValidationUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KaryawanTrainingService {
    private final KaryawanRepository karyawanRepo;
    private final TrainingRepository trainingRepo;
    private final KaryawanTrainingRepository karyawanTrainingRepo;
    private final ValidationUtility validationUtility;
    private final FindById findById;

    public KaryawanTrainingRs insertKaryawanTraining(KaryawanTrainingRq trainingRq) {
        validationUtility.validate(trainingRq);
        Karyawan karyawan = findKaryawanById(trainingRq.getKaryawan().getId());
        Training training = findTrainingByIdOrThrow(trainingRq.getTraining().getId());
        KaryawanTraining karyawanTraining = new KaryawanTraining(
                trainingRq.getTanggal(),
                karyawan,
                training,
                new Date()
        );

        KaryawanTraining karyawanRepo = karyawanTrainingRepo.save(karyawanTraining);
        log.info("Successfully saved karyawan training");
        return convertToResponse(karyawanRepo);
    }

    public KaryawanTrainingRs getKaryawanTraining(int id) {
        KaryawanTraining karyawanTraining = findKaryawanTrainingById(id);
        log.info("Successfully get karyawan training with id {}", id);
        return convertToResponse(karyawanTraining);
    }

    public KaryawanTrainingRs updateKaryawanTraining(UpdateKaryawanTrainingRq updateKaryawanTrainingRq) {
        validationUtility.validate(updateKaryawanTrainingRq);
        KaryawanTraining karyawanTraining = findKaryawanTrainingById(updateKaryawanTrainingRq.getId());
        Karyawan karyawan = findKaryawanById(updateKaryawanTrainingRq.getKaryawan().getId());
        Training training = findTrainingByIdOrThrow(updateKaryawanTrainingRq.getTraining().getId());

        // update karyawan training field
        karyawanTraining.setKaryawan(karyawan);
        karyawanTraining.setTraining(training);
        karyawanTraining.setTanggal(updateKaryawanTrainingRq.getTanggal());
        karyawanTraining.setUpdatedDate(new Date());

        karyawanTrainingRepo.save(karyawanTraining);
        log.info("Sucessfully updated karyawan training");
        return convertToResponse(karyawanTraining);
    }

    public Page<KaryawanTrainingRs> getAllKaryawanTraining(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<KaryawanTrainingRs> karyawanTrainings = karyawanTrainingRepo.findAll(pageable).getContent().stream().map(this::convertToResponse).toList();
        log.info("Successfully get all karyawan training");
        return new PageImpl<>(karyawanTrainings);
    }

    public void deleteKaryawanTraining(IdRq idRequest) {
        validationUtility.validate(idRequest);
        KaryawanTraining karyawanTraining = findKaryawanTrainingById(idRequest.getId());
        log.info("Deleted karyawan training with id {}", karyawanTraining.getId());
        karyawanTrainingRepo.delete(karyawanTraining);
    }

    private Karyawan findKaryawanById(int id) {
        return findById.findKaryawanById(karyawanRepo, id);
    }

    private KaryawanTraining findKaryawanTrainingById(int id) {
        return findById.findKaryawanTrainingById(karyawanTrainingRepo, id);
    }

    private Training findTrainingByIdOrThrow(int id) {
        return findById.findTrainingById(trainingRepo, id);
    }

    private KaryawanTrainingRs convertToResponse(KaryawanTraining karyawanTraining) {
        Karyawan karyawan = karyawanTraining.getKaryawan();
        DetailKaryawan detailKaryawan = karyawan.getDetailKaryawan();
        Training training = karyawanTraining.getTraining();

        KaryawanRs karyawanResponse = new KaryawanRs(
                karyawanTraining.getCreatedDate(),
                karyawanTraining.getUpdatedDate(),
                karyawanTraining.getDeletedDate(),
                karyawan.getId(),
                karyawan.getNama(),
                karyawan.getDob(),
                karyawan.getStatus(),
                karyawan.getAlamat(),
                new DetailKaryawanRs(
                        detailKaryawan.getCreatedDate(),
                        detailKaryawan.getDeletedDate(),
                        detailKaryawan.getUpdatedDate(),
                        detailKaryawan.getId(),
                        detailKaryawan.getNik(),
                        detailKaryawan.getNpwp()
                )
        );

        TrainingRs trainingResponse = new TrainingRs(
                karyawanTraining.getTraining().getCreatedDate(),
                karyawanTraining.getUpdatedDate(),
                karyawanTraining.getDeletedDate(),
                training.getId(),
                training.getTema(),
                training.getPengajar()
        );

        return new KaryawanTrainingRs(
                karyawanTraining.getCreatedDate(),
                karyawanTraining.getUpdatedDate(),
                karyawanTraining.getDeletedDate(),
                karyawanTraining.getId(),
                karyawanResponse,
                trainingResponse,
                karyawanTraining.getTanggal()
        );
    }
}

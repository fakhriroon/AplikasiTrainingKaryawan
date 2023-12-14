package com.idstar.trainingkaryawan.service;

import com.idstar.trainingkaryawan.model.db.Training;
import com.idstar.trainingkaryawan.model.request.IdRq;
import com.idstar.trainingkaryawan.model.request.TrainingRq;
import com.idstar.trainingkaryawan.model.request.UpdateTrainingRq;
import com.idstar.trainingkaryawan.model.response.TrainingRs;
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
public class TrainingService {
    private final TrainingRepository trainingRepo;
    private final ValidationUtility validationUtility;
    private final FindById findById;

    public TrainingRs insertTraining(TrainingRq trainingRequest) {
        validationUtility.validate(trainingRequest);
        Training training = new Training(
                trainingRequest.getTema(),
                trainingRequest.getPengajar(),
                new Date()
        );

        Training response = trainingRepo.save(training);
        log.info("Successfully saved training");
        return convertToResponse(response);
    }

    public TrainingRs getTraining(Integer id) {
        Training training = findTrainingByIdOrThrow(id);
        log.info("Successfully get training by id {}", id);
        return convertToResponse(training);
    }

    public TrainingRs updateTraining(UpdateTrainingRq updateTrainingRq) {
        validationUtility.validate(updateTrainingRq);
        Training training = findTrainingByIdOrThrow(updateTrainingRq.getId());
        // update training field
        training.setTema(updateTrainingRq.getTema());
        training.setPengajar(updateTrainingRq.getPengajar());
        training.setUpdatedDate(new Date());

        trainingRepo.save(training);
        log.info("Successfully updated training");
        return convertToResponse(training);
    }

    public Page<TrainingRs> getTrainings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TrainingRs> trainingResponses = trainingRepo.findAll(pageable).getContent().stream().map(this::convertToResponse).toList();

        log.info("Successfully get all training");
        return new PageImpl<>(trainingResponses);
    }

    public void deleteTraining(IdRq idRq) {
        Training training = findTrainingByIdOrThrow(idRq.getId());
        log.info("Deleted training with id {}", training.getId());
        trainingRepo.delete(training);
    }

    private Training findTrainingByIdOrThrow(int id) {
        return findById.findTrainingById(trainingRepo, id);
    }

    private TrainingRs convertToResponse(Training training) {
        return new TrainingRs(
                training.getCreatedDate(),
                training.getUpdatedDate(),
                training.getDeletedDate(),
                training.getId(),
                training.getTema(),
                training.getPengajar()
        );
    }
}

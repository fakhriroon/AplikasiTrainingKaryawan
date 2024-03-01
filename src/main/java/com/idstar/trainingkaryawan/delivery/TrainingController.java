package com.idstar.trainingkaryawan.delivery;

import com.idstar.trainingkaryawan.model.request.IdRq;
import com.idstar.trainingkaryawan.model.request.TrainingRq;
import com.idstar.trainingkaryawan.model.request.UpdateTrainingRq;
import com.idstar.trainingkaryawan.model.response.GeneralRs;
import com.idstar.trainingkaryawan.model.response.TrainingRs;
import com.idstar.trainingkaryawan.service.TrainingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/idstar/training")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingService trainingService;

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public GeneralRs<TrainingRs> insertTraining(@RequestBody @Valid TrainingRq trainingRq) {
        TrainingRs trainingRs = trainingService.insertTraining(trainingRq);
        return new GeneralRs<>(
                200,
                trainingRs,
                "success"
        );
    }

    @GetMapping("/{id}")
    public GeneralRs<TrainingRs> getTraining(@PathVariable("id") int id) {
        TrainingRs trainingRs = trainingService.getTraining(id);
        return new GeneralRs<>(
                200,
                trainingRs,
                "success"
        );
    }

    @PutMapping
    public GeneralRs<TrainingRs> updateTraining(@RequestBody @Valid UpdateTrainingRq updateTrainingRq) {
        TrainingRs trainingRs = trainingService.updateTraining(updateTrainingRq);
        return new GeneralRs<>(
                200,
                trainingRs,
                "success"
        );
    }

    @GetMapping
    public GeneralRs<Page<TrainingRs>> getAllTraining(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<TrainingRs> trainingRss = trainingService.getTrainings(page, size);
        return new GeneralRs<>(
                200,
                trainingRss,
                "success"
        );
    }

    @DeleteMapping
    public GeneralRs<String> deleteTraining(@RequestBody @Valid IdRq idRq) {
        trainingService.deleteTraining(idRq);
        return new GeneralRs<>(
                200,
                "success",
                "success"
        );
    }
}

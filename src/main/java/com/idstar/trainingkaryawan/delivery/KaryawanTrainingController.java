package com.idstar.trainingkaryawan.delivery;

import com.idstar.trainingkaryawan.model.request.IdRq;
import com.idstar.trainingkaryawan.model.request.KaryawanTrainingRq;
import com.idstar.trainingkaryawan.model.request.UpdateKaryawanTrainingRq;
import com.idstar.trainingkaryawan.model.response.GeneralRs;
import com.idstar.trainingkaryawan.model.response.KaryawanTrainingRs;
import com.idstar.trainingkaryawan.service.KaryawanTrainingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/karyawan-trainings")
@RequiredArgsConstructor
public class KaryawanTrainingController {
    private final KaryawanTrainingService karyawanTrainingService;

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public GeneralRs<KaryawanTrainingRs> insertKaryawanTraining(@RequestBody @Valid KaryawanTrainingRq karyawanTrainingRq) {
        KaryawanTrainingRs karyawanTrainingResponse = karyawanTrainingService.insertKaryawanTraining(karyawanTrainingRq);
        return new GeneralRs<>(
                200,
                karyawanTrainingResponse,
                "success"
        );
    }

    @GetMapping("/{id}")
    public GeneralRs<KaryawanTrainingRs> getTraining(@PathVariable("id") int id) {
        KaryawanTrainingRs trainingResponse = karyawanTrainingService.getKaryawanTraining(id);
        return new GeneralRs<>(
                200,
                trainingResponse,
                "success"
        );
    }

    @PutMapping
    public GeneralRs<KaryawanTrainingRs> updateTraining(@RequestBody @Valid UpdateKaryawanTrainingRq updateKaryawanTrainingRq) {
        KaryawanTrainingRs karyawanTrainingResponse = karyawanTrainingService.updateKaryawanTraining(updateKaryawanTrainingRq);
        return new GeneralRs<>(
                200,
                karyawanTrainingResponse,
                "success"
        );
    }

    @GetMapping
    public GeneralRs<Page<KaryawanTrainingRs>> getAllKaryawanTraining(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<KaryawanTrainingRs> karyawanTrainingResponses = karyawanTrainingService.getAllKaryawanTraining(page, size);
        return new GeneralRs<>(
                200,
                karyawanTrainingResponses,
                "success"
        );
    }

    @DeleteMapping
    public GeneralRs<String> deleteTraining(@RequestBody @Valid IdRq idRq) {
        karyawanTrainingService.deleteKaryawanTraining(idRq);
        return new GeneralRs<>(
                200,
                "success",
                "success"
        );
    }
}

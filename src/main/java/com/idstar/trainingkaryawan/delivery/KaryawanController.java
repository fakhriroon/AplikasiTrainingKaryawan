package com.idstar.trainingkaryawan.delivery;

import com.idstar.trainingkaryawan.model.request.IdRq;
import com.idstar.trainingkaryawan.model.request.KaryawanRq;
import com.idstar.trainingkaryawan.model.request.UpdateKaryawanRq;
import com.idstar.trainingkaryawan.model.response.GeneralRs;
import com.idstar.trainingkaryawan.model.response.KaryawanRs;
import com.idstar.trainingkaryawan.service.KaryawanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/idstar/karyawan")
public class KaryawanController {

    private final KaryawanService karyawanService;

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public GeneralRs<KaryawanRs> insertKaryawan(@RequestBody @Valid KaryawanRq karyawanRq) {
        KaryawanRs karyawanResponse = karyawanService.insertKaryawan(karyawanRq);

        return new GeneralRs<>(
                200,
                karyawanResponse,
                "success"
        );
    }

    @GetMapping("/{id}")
    public GeneralRs<KaryawanRs> getKaryawan(@PathVariable("id") Integer id) {
        KaryawanRs karyawanResponse = karyawanService.getKaryawan(id);

        return new GeneralRs<>(
                200,
                karyawanResponse,
                "success"
        );
    }

    @PutMapping
    public GeneralRs<KaryawanRs> updateKaryawan(@RequestBody @Valid UpdateKaryawanRq updateKaryawanRq) {
        KaryawanRs karyawanResponse = karyawanService.updateKaryawan(updateKaryawanRq);
        return new GeneralRs<>(
                200,
                karyawanResponse,
                "success"
        );
    }

    @GetMapping
    public GeneralRs<Page<KaryawanRs>> getAllKaryawan(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<KaryawanRs> karyawanResponse = karyawanService.getAllKaryawan(page, size);
        return new GeneralRs<>(
                200,
                karyawanResponse,
                "success"
        );
    }

    @DeleteMapping
    public GeneralRs<String> deleteKaryawan(@RequestBody @Valid IdRq idRq) {
        karyawanService.deleteKaryawan(idRq);
        return new GeneralRs<>(
                200,
                "success",
                "success"
        );
    }


}

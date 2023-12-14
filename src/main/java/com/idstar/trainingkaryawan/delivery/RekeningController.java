package com.idstar.trainingkaryawan.delivery;

import com.idstar.trainingkaryawan.model.request.IdRq;
import com.idstar.trainingkaryawan.model.request.RekeningRq;
import com.idstar.trainingkaryawan.model.request.UpdateRekeningRq;
import com.idstar.trainingkaryawan.model.response.GeneralRs;
import com.idstar.trainingkaryawan.model.response.RekeningRs;
import com.idstar.trainingkaryawan.service.RekeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rekenings")
@RequiredArgsConstructor
public class RekeningController {
    private final RekeningService rekeningService;

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public GeneralRs<RekeningRs> insertRekening(@RequestBody @Valid RekeningRq rekeningRq) {
        RekeningRs rekeningRs = rekeningService.insertRekening(rekeningRq);
        return new GeneralRs<>(
                200,
                rekeningRs,
                "success"
        );
    }

    @GetMapping("/{id}")
    public GeneralRs<RekeningRs> getRekening(@PathVariable("id") int id) {
        RekeningRs rekeningRs = rekeningService.getRekening(id);
        return new GeneralRs<>(
                200,
                rekeningRs,
                "success"
        );
    }

    @PutMapping
    public GeneralRs<RekeningRs> updateRekening(@RequestBody @Valid UpdateRekeningRq updateRekeningRq) {
        RekeningRs rekeningRs = rekeningService.updateRekening(updateRekeningRq);
        return new GeneralRs<>(
                200,
                rekeningRs,
                "success"
        );
    }

    @GetMapping
    public GeneralRs<Page<RekeningRs>> getAllRekening(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<RekeningRs> rekeningRss = rekeningService.getAllRekening(page, size);
        return new GeneralRs<>(
                200,
                rekeningRss,
                "success"
        );
    }

    @DeleteMapping
    public GeneralRs<String> deleteRekening(@RequestBody @Valid IdRq idRq) {
        rekeningService.deleteRekening(idRq);
        return new GeneralRs<>(
                200,
                "success",
                "success"
        );
    }
}

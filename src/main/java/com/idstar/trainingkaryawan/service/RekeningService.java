package com.idstar.trainingkaryawan.service;

import com.idstar.trainingkaryawan.model.db.Karyawan;
import com.idstar.trainingkaryawan.model.db.Rekening;
import com.idstar.trainingkaryawan.model.request.IdRq;
import com.idstar.trainingkaryawan.model.request.RekeningRq;
import com.idstar.trainingkaryawan.model.request.UpdateRekeningRq;
import com.idstar.trainingkaryawan.model.response.KaryawanRekeningRs;
import com.idstar.trainingkaryawan.model.response.RekeningRs;
import com.idstar.trainingkaryawan.repository.KaryawanRepository;
import com.idstar.trainingkaryawan.repository.RekeningRepository;
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
public class RekeningService {
    private final RekeningRepository rekeningRepository;
    private final KaryawanRepository karyawanRepository;
    private final ValidationUtility validationUtility;
    private final FindById itemByIdOrThrow;

    public RekeningRs insertRekening(RekeningRq rekeningRq) {
        validationUtility.validate(rekeningRq);

        Karyawan karyawan = findKaryawanByIdOrThrow(rekeningRq.getKaryawan().getId());

        Rekening rekening = new Rekening(
                rekeningRq.getJenis(),
                rekeningRq.getNama(),
                rekeningRq.getRekening(),
                rekeningRq.getAlamat(),
                karyawan,
                new Date()
        );
        return convertToResponse(rekeningRepository.save(rekening));
    }

    public RekeningRs getRekening(int id) {
        validationUtility.validate(id);
        Rekening rekening = findRekeningByIdOrThrow(id);
        log.info("Successfully get training with id {}", id);
        return convertToResponse(rekening);
    }

    public RekeningRs updateRekening(UpdateRekeningRq updateRekeningRq) {
        validationUtility.validate(updateRekeningRq);
        Rekening rekening = findRekeningByIdOrThrow(updateRekeningRq.getId());
        Karyawan karyawan = findKaryawanByIdOrThrow(updateRekeningRq.getKaryawan().getId());

        // update the field rekening
        rekening.setNama(updateRekeningRq.getNama());
        rekening.setJenis(updateRekeningRq.getJenis());
        rekening.setRekening(updateRekeningRq.getRekening());
        rekening.setAlamat(updateRekeningRq.getAlamat());
        rekening.setUpdatedDate(new Date());
        rekening.setKaryawan(karyawan);

        rekeningRepository.save(rekening);
        log.info("Successfully updated rekening data");
        return convertToResponse(rekening);
    }

    public Page<RekeningRs> getAllRekening(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<RekeningRs> rekeningResponses = rekeningRepository.findAll(pageable).getContent().stream().map(this::convertToResponse).toList();

        log.info("Successfully get all rekening");
        return new PageImpl<>(rekeningResponses);
    }

    public void deleteRekening(IdRq idRq) {
        validationUtility.validate(idRq);
        Rekening rekening = findRekeningByIdOrThrow(idRq.getId());
        log.info("Deleted Rekening with id {}", idRq.getId());
        rekeningRepository.delete(rekening);
    }

    private Rekening findRekeningByIdOrThrow(int id) {
        return itemByIdOrThrow.findRekeningById(rekeningRepository, id);
    }

    private Karyawan findKaryawanByIdOrThrow(Integer id) {
        return itemByIdOrThrow.findKaryawanById(karyawanRepository, id);
    }

    private RekeningRs convertToResponse(Rekening rekening) {
        return new RekeningRs(
                rekening.getCreatedDate(),
                rekening.getUpdatedDate(),
                rekening.getDeletedDate(),
                rekening.getId(),
                rekening.getNama(),
                rekening.getJenis(),
                rekening.getRekening(),
                rekening.getAlamat(),
                new KaryawanRekeningRs(
                        rekening.getKaryawan().getId().toString(),
                        rekening.getKaryawan().getNama()
                )
        );
    }
}

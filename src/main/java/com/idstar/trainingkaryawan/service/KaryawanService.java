package com.idstar.trainingkaryawan.service;

import com.idstar.trainingkaryawan.model.db.DetailKaryawan;
import com.idstar.trainingkaryawan.model.db.Karyawan;
import com.idstar.trainingkaryawan.model.request.IdRq;
import com.idstar.trainingkaryawan.model.request.KaryawanRq;
import com.idstar.trainingkaryawan.model.request.UpdateKaryawanRq;
import com.idstar.trainingkaryawan.model.response.DetailKaryawanRs;
import com.idstar.trainingkaryawan.model.response.KaryawanRs;
import com.idstar.trainingkaryawan.repository.KaryawanRepository;
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
public class KaryawanService {

    private final KaryawanRepository karyawanRepo;
    private final ValidationUtility validationUtility;
    private final FindById findById;

    public KaryawanRs insertKaryawan(KaryawanRq karyawanRq) {
        validationUtility.validate(karyawanRq);

        Karyawan karyawan = new Karyawan(
                karyawanRq.getNama(),
                karyawanRq.getDob(),
                karyawanRq.getStatus(),
                karyawanRq.getAlamat(),
                new DetailKaryawan(
                        new Date(),
                        null,
                        null,
                        karyawanRq. getDetailKaryawan().getNik(),
                        karyawanRq.getDetailKaryawan().getNpwp()
                ),
                new Date()
        );
        Karyawan response = karyawanRepo.save(karyawan);
        log.info("Karyawan saved successfully!");
        return convertToResponse(response);
    }

    public KaryawanRs getKaryawan(Integer id) {
        Karyawan karyawan = findKaryawanById(id);

        log.info("Success get karyawan by id {}", id);
        return convertToResponse(karyawan);
    }

    public KaryawanRs updateKaryawan(UpdateKaryawanRq updateKaryawanRq) {
        validationUtility.validate(updateKaryawanRq);
        Karyawan karyawan = findKaryawanById(updateKaryawanRq.getId());

        // update the karyawan field
        karyawan.setNama(updateKaryawanRq.getNama());
        karyawan.setDob(updateKaryawanRq.getDob());
        karyawan.setStatus(updateKaryawanRq.getStatus());
        karyawan.setAlamat(updateKaryawanRq.getAlamat());
        karyawan.setUpdatedDate(new Date());
        karyawan.getDetailKaryawan().setNik(updateKaryawanRq.getDetailKaryawan().getNik());
        karyawan.getDetailKaryawan().setNpwp(updateKaryawanRq.getDetailKaryawan().getNpwp());
        karyawan.getDetailKaryawan().setUpdatedDate(new Date());
        karyawanRepo.save(karyawan);
        log.info("Successfully updated karyawan");
        return convertToResponse(karyawan);
    }

    public Page<KaryawanRs> getAllKaryawan(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        List<KaryawanRs> data = karyawanRepo.findAll(paging).getContent().stream().map(this::convertToResponse).toList();

        log.info("Successfully get all karyawan");
        return new PageImpl<>(data);
    }

    public void deleteKaryawan(IdRq idRequest) {
        validationUtility.validate(idRequest);
        Karyawan karyawan = findKaryawanById(idRequest.getId());
        log.info("Deleted karyawan with id {}", karyawan.getId());
        karyawanRepo.delete(karyawan);
    }

    private Karyawan findKaryawanById(Integer id) {
        return findById.findKaryawanById(karyawanRepo, id);
    }

    private KaryawanRs convertToResponse(Karyawan karyawan) {
        return new KaryawanRs(
                karyawan.getCreatedDate(),
                karyawan.getUpdatedDate(),
                karyawan.getDeletedDate(),
                karyawan.getId(),
                karyawan.getNama(),
                karyawan.getDob(),
                karyawan.getStatus(),
                karyawan.getAlamat(),
                new DetailKaryawanRs(
                        karyawan.getDetailKaryawan().getCreatedDate(),
                        karyawan.getDetailKaryawan().getDeletedDate(),
                        karyawan.getDetailKaryawan().getUpdatedDate(),
                        karyawan.getDetailKaryawan().getId(),
                        karyawan.getDetailKaryawan().getNik(),
                        karyawan.getDetailKaryawan().getNpwp()
                )
        );
    }

}

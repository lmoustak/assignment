package com.laertismoustakas.demo.repositories;

import com.laertismoustakas.demo.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordDao extends JpaRepository<MedicalRecord, Long> {

  MedicalRecord findByCode(String code);
}

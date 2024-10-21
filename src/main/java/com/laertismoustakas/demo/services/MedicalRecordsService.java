package com.laertismoustakas.demo.services;

import com.laertismoustakas.demo.dtos.MedicalRecordDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface MedicalRecordsService {

  List<MedicalRecordDto> createFromCsv(MultipartFile csvFile) throws Exception;

  List<MedicalRecordDto> readAllMedicalRecords();

  MedicalRecordDto readMedicalRecordByCode(String code);

  boolean deleteAllMedicalRecords();

}

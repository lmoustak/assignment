package com.laertismoustakas.demo.services.impl;

import com.laertismoustakas.demo.dtos.MedicalRecordDto;
import com.laertismoustakas.demo.entities.MedicalRecord;
import com.laertismoustakas.demo.repositories.MedicalRecordDao;
import com.laertismoustakas.demo.services.MedicalRecordsService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;

@Service
public class MedicalRecordsServiceImpl implements MedicalRecordsService {

  private static final Logger logger = LoggerFactory.getLogger(MedicalRecordsServiceImpl.class);
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
      "dd-MM-yyyy");

  private final MedicalRecordDao medicalRecordDao;

  public MedicalRecordsServiceImpl(MedicalRecordDao medicalRecordDao) {
    this.medicalRecordDao = medicalRecordDao;
  }

  private static final Function<CSVRecord, MedicalRecord> csvRecordToMedicalRecord = csvRecord -> {
    var medicalRecord = new MedicalRecord();

    String source = csvRecord.get("source");
    if (source != null && !source.isBlank()) {
      medicalRecord.setSource(source);
    }

    String codeListCode = csvRecord.get("codeListCode");
    if (codeListCode != null && !codeListCode.isBlank()) {
      medicalRecord.setCodeListCode(codeListCode);
    }

    String code = csvRecord.get("code");
    if (code != null && !code.isBlank()) {
      medicalRecord.setCode(code);
    }

    String displayValue = csvRecord.get("displayValue");
    if (displayValue != null && !displayValue.isBlank()) {
      medicalRecord.setDisplayValue(displayValue);
    }

    String longDescription = csvRecord.get("longDescription");
    if (longDescription != null && !longDescription.isBlank()) {
      medicalRecord.setLongDescription(longDescription);
    }

    String fromDate = csvRecord.get("fromDate");
    if (fromDate != null && !fromDate.isBlank()) {
      medicalRecord.setFromDate(LocalDate.parse(fromDate, dateTimeFormatter));
    }

    String toDate = csvRecord.get("toDate");
    if (toDate != null && !toDate.isBlank()) {
      medicalRecord.setToDate(LocalDate.parse(toDate, dateTimeFormatter));
    }

    String sortingPriority = csvRecord.get("sortingPriority");
    if (sortingPriority != null && !sortingPriority.isBlank()) {
      medicalRecord.setSortingPriority(Integer.parseInt(sortingPriority));
    }

    return medicalRecord;
  };

  @Override
  public List<MedicalRecordDto> createFromCsv(MultipartFile csvFile) throws Exception {
    try (
        var inputStreamReader = new InputStreamReader(csvFile.getInputStream(),
            StandardCharsets.UTF_8);
        var bufferedReader = new BufferedReader(inputStreamReader);
        CSVParser csvParser = CSVFormat.DEFAULT.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .build()
            .parse(bufferedReader)
    ) {
      List<MedicalRecord> medicalRecords = csvParser.stream()
          .map(csvRecordToMedicalRecord)
          .toList();

      medicalRecords = medicalRecordDao.saveAll(medicalRecords);

      return medicalRecords.stream()
          .map(MedicalRecordDto::fromEntity)
          .toList();
    } catch (Exception e) {
      logger.error("Error while parsing CSV:", e);
      throw e;
    }
  }

  @Override
  public List<MedicalRecordDto> readAllMedicalRecords() {
    return medicalRecordDao.findAll()
        .stream()
        .map(MedicalRecordDto::fromEntity)
        .toList();
  }

  @Override
  public MedicalRecordDto readMedicalRecordByCode(String code) {
    MedicalRecord entity = medicalRecordDao.findByCode(code);
    logger.info("Found medical record: {} for code '{}'", entity, code);
    return MedicalRecordDto.fromEntity(entity);
  }

  @Override
  public boolean deleteAllMedicalRecords() {
    try {
      medicalRecordDao.deleteAll();
      return true;
    } catch (Exception e) {
      logger.error("Error while deleting all medical records:", e);
      throw e;
    }
  }
}

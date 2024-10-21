package com.laertismoustakas.demo.web;

import com.laertismoustakas.demo.dtos.MedicalRecordDto;
import com.laertismoustakas.demo.services.MedicalRecordsService;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/medicalRecords", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
public class MedicalRecordEndpoint {

  private final MedicalRecordsService medicalRecordsService;

  public MedicalRecordEndpoint(MedicalRecordsService medicalRecordsService) {
    this.medicalRecordsService = medicalRecordsService;
  }

  @PostMapping
  public ResponseEntity<List<MedicalRecordDto>> createFromCsv(@RequestBody MultipartFile csvFile)
      throws Exception {
    return ResponseEntity.created(URI.create("/medicalRecords"))
        .body(medicalRecordsService.createFromCsv(csvFile));
  }

  @GetMapping
  public ResponseEntity<List<MedicalRecordDto>> readAllMedicalRecords() {
    return ResponseEntity.ok(medicalRecordsService.readAllMedicalRecords());
  }

  @GetMapping("/code/{code}")
  public ResponseEntity<MedicalRecordDto> readMedicalRecordByCode(@PathVariable String code) {
    return ResponseEntity.ok(medicalRecordsService.readMedicalRecordByCode(code));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAllMedicalRecords() {
    medicalRecordsService.deleteAllMedicalRecords();
    return ResponseEntity.noContent()
        .build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetail> handleException(Exception e) {
    var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
        e.getMessage());
    return ResponseEntity.internalServerError()
        .body(problemDetails);
  }

}

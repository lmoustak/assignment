package com.laertismoustakas.demo.dtos;

import com.laertismoustakas.demo.entities.MedicalRecord;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MedicalRecordDto implements Serializable {

  private Long id;
  private String source;
  private String codeListCode;
  private String code;
  private String displayValue;
  private String longDescription;
  private LocalDate fromDate;
  private LocalDate toDate;
  private Integer sortingPriority;

  public MedicalRecordDto() {
  }

  public Long getId() {
    return id;
  }

  public MedicalRecordDto setId(Long id) {
    this.id = id;
    return this;
  }

  public String getSource() {
    return source;
  }

  public MedicalRecordDto setSource(String source) {
    this.source = source;
    return this;
  }

  public String getCodeListCode() {
    return codeListCode;
  }

  public MedicalRecordDto setCodeListCode(String codeListCode) {
    this.codeListCode = codeListCode;
    return this;
  }

  public String getCode() {
    return code;
  }

  public MedicalRecordDto setCode(String code) {
    this.code = code;
    return this;
  }

  public String getDisplayValue() {
    return displayValue;
  }

  public MedicalRecordDto setDisplayValue(String displayValue) {
    this.displayValue = displayValue;
    return this;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public MedicalRecordDto setLongDescription(String longDescription) {
    this.longDescription = longDescription;
    return this;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public MedicalRecordDto setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
    return this;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public MedicalRecordDto setToDate(LocalDate toDate) {
    this.toDate = toDate;
    return this;
  }

  public Integer getSortingPriority() {
    return sortingPriority;
  }

  public MedicalRecordDto setSortingPriority(Integer sortingPriority) {
    this.sortingPriority = sortingPriority;
    return this;
  }

  public MedicalRecordDto(Long id, String source, String codeListCode, String code,
      String displayValue, String longDescription, LocalDate fromDate, LocalDate toDate,
      Integer sortingPriority) {
    this.id = id;
    this.source = source;
    this.codeListCode = codeListCode;
    this.code = code;
    this.displayValue = displayValue;
    this.longDescription = longDescription;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.sortingPriority = sortingPriority;
  }

  public static MedicalRecordDto fromEntity(MedicalRecord entity) {
    if (entity == null) {
      return null;
    }

    return new MedicalRecordDto(
        entity.getId(),
        entity.getSource(),
        entity.getCodeListCode(),
        entity.getCode(),
        entity.getDisplayValue(),
        entity.getLongDescription(),
        entity.getFromDate(),
        entity.getToDate(),
        entity.getSortingPriority()
    );
  }

  public MedicalRecord toEntity() {
    var entity = new MedicalRecord();
    entity.setSource(source);
    entity.setCodeListCode(codeListCode);
    entity.setCode(code);
    entity.setDisplayValue(displayValue);
    entity.setLongDescription(longDescription);
    entity.setFromDate(fromDate);
    entity.setToDate(toDate);
    entity.setSortingPriority(sortingPriority);
    return entity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MedicalRecordDto that = (MedicalRecordDto) o;
    return Objects.equals(id, that.id) && Objects.equals(source, that.source)
        && Objects.equals(codeListCode, that.codeListCode) && Objects.equals(code,
        that.code) && Objects.equals(displayValue, that.displayValue)
        && Objects.equals(longDescription, that.longDescription)
        && Objects.equals(fromDate, that.fromDate) && Objects.equals(toDate,
        that.toDate) && Objects.equals(sortingPriority, that.sortingPriority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, source, codeListCode, code, displayValue, longDescription, fromDate,
        toDate, sortingPriority);
  }

  @Override
  public String toString() {
    return "MedicalRecordDto{" +
        "id=" + id +
        ", source='" + source + '\'' +
        ", codeListCode='" + codeListCode + '\'' +
        ", code='" + code + '\'' +
        ", displayValue='" + displayValue + '\'' +
        ", longDescription='" + longDescription + '\'' +
        ", fromDate=" + fromDate +
        ", toDate=" + toDate +
        ", sortingPriority=" + sortingPriority +
        '}';
  }
}

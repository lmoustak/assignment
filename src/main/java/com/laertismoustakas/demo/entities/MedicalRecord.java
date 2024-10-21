package com.laertismoustakas.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "MEDICAL_RECORDS")
public class MedicalRecord implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String source;

  @Column
  private String codeListCode;

  @Column(unique = true)
  private String code;

  @Column
  private String displayValue;

  @Column
  private String longDescription;

  @Column
  private LocalDate fromDate;

  @Column
  private LocalDate toDate;

  @Column
  private Integer sortingPriority;

  public MedicalRecord() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getCodeListCode() {
    return codeListCode;
  }

  public void setCodeListCode(String codeListCode) {
    this.codeListCode = codeListCode;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDisplayValue() {
    return displayValue;
  }

  public void setDisplayValue(String displayValue) {
    this.displayValue = displayValue;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }

  public Integer getSortingPriority() {
    return sortingPriority;
  }

  public void setSortingPriority(Integer sortingPriority) {
    this.sortingPriority = sortingPriority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MedicalRecord that = (MedicalRecord) o;
    return id == that.id && Objects.equals(source, that.source) && Objects.equals(
        codeListCode, that.codeListCode) && Objects.equals(code, that.code)
        && Objects.equals(displayValue, that.displayValue) && Objects.equals(
        longDescription, that.longDescription) && Objects.equals(fromDate, that.fromDate)
        && Objects.equals(toDate, that.toDate) && Objects.equals(sortingPriority,
        that.sortingPriority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, source, codeListCode, code, displayValue, longDescription, fromDate,
        toDate, sortingPriority);
  }

  @Override
  public String toString() {
    return "MedicalRecord{" +
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

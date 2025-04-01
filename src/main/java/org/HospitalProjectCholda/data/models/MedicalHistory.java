package org.HospitalProjectCholda.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Medical History")
public class MedicalHistory {
    @Id
    private String id;
    private LocalDateTime recordDate;
    private String description;
    private String treatment;

//    public MedicalHistory(String recordDate, String description, String treatment) {
//        this.recordDate =  LocalDate.parse(recordDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        this.description = description;
//        this.treatment = treatment;
//    }
}


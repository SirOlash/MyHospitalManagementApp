package org.HospitalProjectCholda.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Document(collection = "patient")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
    @Id
    private String id;;
    @DBRef
    private PatientProfile patientProfile;
    private String userName;
    private String email;
    private String password;
    @DBRef
    private List<MedicalHistory> medicalHistory;


//    public Patient(String userName, String email, String password) {
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//
//    }


}

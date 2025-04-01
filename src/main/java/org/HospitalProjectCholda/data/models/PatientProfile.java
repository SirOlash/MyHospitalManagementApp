package org.HospitalProjectCholda.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "patientProfile")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientProfile extends UserProfile{
    private LocalDate dateOfBirth;
    private Gender gender;

//    public PatientProfile(String firstName, String lastName, String phoneNumber,String address, String dateOfBirth, Gender gender) {
//        super(firstName,lastName,phoneNumber,address);
//        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        this.gender = gender;
//    }
}

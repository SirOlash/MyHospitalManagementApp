package org.HospitalProjectCholda.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "doctor")
public class Doctor {
    @Id
    private String id;
    @DBRef
    private DoctorProfile doctorProfile;
    private String userName;
    private String email;
    private String password;
    private boolean isAvailable = true;

//    public Doctor(String userName, String email, String password) {
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//        this.id = id;
//    }



}

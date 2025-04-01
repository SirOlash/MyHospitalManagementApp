package org.HospitalProjectCholda.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfile {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

//    public UserProfile(String firstName, String lastName, String phoneNumber, String address) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.address = address;
//    }
}

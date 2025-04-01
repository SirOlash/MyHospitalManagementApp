package org.HospitalProjectCholda.controllers;

import org.HospitalProjectCholda.data.models.Patient;
import org.HospitalProjectCholda.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientControllers {

    @Autowired
    private PatientServices patientServices;

    @PostMapping("/register")
    public Patient registerPatient(@RequestBody Patient patient) {
        return patientServices.createNewPatient(patient);
    }
//    @PostMapping("/login")
//    public Patient loginPatient(@RequestBody String email) {
//
//    }
}
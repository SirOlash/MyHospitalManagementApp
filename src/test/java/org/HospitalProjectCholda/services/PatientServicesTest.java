package org.HospitalProjectCholda.services;

import org.HospitalProjectCholda.data.models.Patient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServicesTest {
    @Autowired
    private PatientServices patientServices;

    @BeforeEach
    void setUp() {
        patientServices.deleteAll();
    }

    @AfterEach
    void tearDown() {
        patientServices.deleteAll();
    }

    @Test
    public void registerPatient() {
        Patient patient = new Patient();
        patient.setUserName("test");
        patient.setEmail("test@test.com");
        patient.setPassword("password");
        Patient newPatient = patientServices.createNewPatient(patient);
        assertNotNull(newPatient);
        assertEquals(1, patientServices.count());

    }

    @Test
    public void getPatient() {
        Patient patient = patientServices.createNewPatient(new Patient());
        patient.setUserName("test");
    }
}
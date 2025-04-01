package org.HospitalProjectCholda.services;

import org.HospitalProjectCholda.data.models.Doctor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorServicesTest {
    @Autowired
    private DoctorServices doctorServices;

    @BeforeEach
    void setUp() {
        doctorServices.deleteAll();
    }

    @AfterEach
    void tearDown() {
        doctorServices.deleteAll();
    }

    @Test
    public void testThatYouCanRegisterDoctor() {
        Doctor doctor = new Doctor();
        doctor.setUserName("chibuzor");
        doctor.setEmail("chibuzor@gmail.com");
        doctor.setPassword("1234");
        Doctor newDoctor = doctorServices.createNewDoctor(doctor);
        assertNotNull(newDoctor);
        assertEquals(1,doctorServices.count());
    }
}
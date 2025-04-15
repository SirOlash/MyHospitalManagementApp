package org.HospitalProjectCholda.services;

import org.HospitalProjectCholda.data.models.Doctor;
import org.HospitalProjectCholda.data.models.Patient;

import org.HospitalProjectCholda.dto.request.UserRegistrationRequest;
import org.HospitalProjectCholda.exceptions.DuplicateUserException;
import org.HospitalProjectCholda.exceptions.InvalidUserNameOrPasswordException;
import org.HospitalProjectCholda.services.doctorservices.DoctorServices;
import org.HospitalProjectCholda.services.patientservices.PatientServices;
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

    @Autowired
    private DoctorServices doctorServices;

    private Patient newPatient;
    private Doctor newDoctor;

    @BeforeEach
    void setUp() {
        patientServices.deleteAll();

        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUserName("test");
        request.setEmail("test@test.com");
        request.setPassword("password");

        newPatient = patientServices.createNewPatient(request);

        Doctor doctor = new Doctor();
        doctor.setUserName("chibuzor");
        doctor.setEmail("chibuzor@gmail.com");
        doctor.setPassword("1234");
        newDoctor = doctorServices.createNewDoctor(doctor);

    }

    @AfterEach
    void tearDown() {
        patientServices.deleteAll();
    }

    @Test
    public void testPatientCanBeRegistered() {

        assertNotNull(newPatient);
        assertEquals(1, patientServices.count());

    }
    @Test
    public void testThatDuplicateUserExceptionIsThrownIfSamePatientRegistersTwice() {
        UserRegistrationRequest request2 = new UserRegistrationRequest();
        request2.setUserName("test");
        request2.setEmail("test@test.com");
        request2.setPassword("password");

        assertThrows(DuplicateUserException.class, () -> patientServices.createNewPatient(request2));

    }

    @Test
    public void testPatientCanLogin() {
        Patient loggedInPatient = patientServices.loginPatient("test@test.com", "password");
        assertNotNull(loggedInPatient);
        assertEquals(newPatient.getEmail(), loggedInPatient.getEmail());
    }

    @Test
    public void testInvalidUserNameOrPasswordExceptionIsThrownIfEmailOrPasswordIsInvalid() {
        assertThrows(InvalidUserNameOrPasswordException.class, () -> patientServices.loginPatient("test@test.com", "wrongpassword"));
        assertThrows(InvalidUserNameOrPasswordException.class, () -> patientServices.loginPatient("wrongEmail@test.com", "password"));
    }

    @Test
    public void testThatPatientCanBookAppointment() {
        Patient loggedInPatient = patientServices.loginPatient("test@test.com", "password");

    }
}
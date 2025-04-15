package org.HospitalProjectCholda.services;

import org.HospitalProjectCholda.data.models.Doctor;
import org.HospitalProjectCholda.dto.request.UserRegistrationRequest;
import org.HospitalProjectCholda.exceptions.DuplicateUserException;
import org.HospitalProjectCholda.services.doctorservices.DoctorServices;
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

    private Doctor newDoctor;

    @BeforeEach
    void setUp() {
        doctorServices.deleteAll();

        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUserName("chibuzor");
        request.setEmail("chibuzor@gmail.com");
        request.setPassword("1234");
        newDoctor = doctorServices.createNewDoctor(request);
    }

    @AfterEach
    void tearDown() {
        doctorServices.deleteAll();
    }

    @Test
    public void testThatYouCanRegisterDoctor() {

        assertNotNull(newDoctor);
        assertEquals(1,doctorServices.count());
    }

    @Test
    public void testThatExceptionIsThrownWhenDoctorWithSimilarRegisterTwice(){
        assertEquals(1, doctorServices.count());

        UserRegistrationRequest request2 = new UserRegistrationRequest();
        request2.setUserName("chibuzo");
        request2.setEmail("chibuzor@gmail.com");
        request2.setPassword("1234");

        DuplicateUserException exception = assertThrows(DuplicateUserException.class, () -> {
            doctorServices.createNewDoctor(request2);
        });
        assertEquals("Doctor with email chibuzor@gmail.com already exists", exception.getMessage());
    }

    @Test
    public void testThatDoctorCanLogin() {
        Doctor currentDoctor = doctorServices.loginDoctor("chibuzor@gmail.com", "1234");
        assertNotNull(currentDoctor);
        assertEquals(newDoctor.getEmail(), currentDoctor.getEmail());
    }

}
package org.HospitalProjectCholda.services.appointmentservices;

import org.HospitalProjectCholda.data.models.Appointment;
import org.HospitalProjectCholda.data.models.Doctor;
import org.HospitalProjectCholda.data.models.Patient;
import org.HospitalProjectCholda.dto.request.AppointmentRequest;
import org.HospitalProjectCholda.dto.request.UserRegistrationRequest;
import org.HospitalProjectCholda.services.doctorservices.DoctorServices;
import org.HospitalProjectCholda.services.patientservices.PatientServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServicesTest {
    @Autowired
    private AppointmentServices appointmentServices;

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private DoctorServices doctorServices;

    private AppointmentRequest appointmentRequest;

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

        UserRegistrationRequest docRequest = new UserRegistrationRequest();
        docRequest.setUserName("chibuzor");
        docRequest.setEmail("chibuzor@gmail.com");
        docRequest.setPassword("1234");
        newDoctor = doctorServices.createNewDoctor(docRequest);

        appointmentRequest = new AppointmentRequest();
//        appointmentRequest.setAppointmentTime(LocalDateTime.of(2025, 4, 10, 15, 30));
        appointmentRequest.setAppointmentTime(LocalDateTime.now());
        appointmentRequest.setDescription("I have headache");
        appointmentRequest.setDoctorEmail("chibuzor@gmail.com");


    }

    @AfterEach
    void tearDown() {
        appointmentServices.deleteAll();
    }

    @Test
    public void testThatPatientCanBookAppointment() {
        Appointment bookedAppointment = appointmentServices.createAppointment(newPatient, appointmentRequest);
        assertNotNull(bookedAppointment);
        assertEquals("I have headache", bookedAppointment.getDescription());
        assertEquals("chibuzor", bookedAppointment.getDoctor().getUserName());
    }

    @Test
    public void testThatDoctorIsAvailableStatusChangesToFalseAfterBeenBooked() {
        Appointment bookedAppointment = appointmentServices.createAppointment(newPatient, appointmentRequest);
        assertFalse(bookedAppointment.getDoctor().isAvailable());
    }

    @Test
    public void testThatDoctorCanAcceptAppointment() {
        Appointment bookedAppointment = appointmentServices.createAppointment(newPatient, appointmentRequest);
        assertEquals("I have headache", bookedAppointment.getDescription());

        appointmentServices.acceptAppointment("chibuzor@gmail.com", "Take paracetamol");

    }


//    @Test

}

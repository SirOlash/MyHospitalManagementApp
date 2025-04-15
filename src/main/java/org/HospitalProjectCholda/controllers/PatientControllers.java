package org.HospitalProjectCholda.controllers;

import jakarta.validation.Valid;
import org.HospitalProjectCholda.data.models.Appointment;
import org.HospitalProjectCholda.data.models.Doctor;
import org.HospitalProjectCholda.data.models.Patient;
import org.HospitalProjectCholda.data.repositories.PatientRepository;
import org.HospitalProjectCholda.dto.request.AppointmentRequest;
import org.HospitalProjectCholda.dto.request.UserLoginRequest;
import org.HospitalProjectCholda.dto.request.UserRegistrationRequest;
import org.HospitalProjectCholda.dto.response.UserRegistrationResponse;
import org.HospitalProjectCholda.exceptions.InvalidPatientException;
import org.HospitalProjectCholda.services.appointmentservices.AppointmentServices;
import org.HospitalProjectCholda.services.doctorservices.DoctorServices;
import org.HospitalProjectCholda.services.patientservices.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientControllers {

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private DoctorServices doctorServices;
    @Autowired
    private AppointmentServices appointmentServices;

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerPatient(@Valid @RequestBody UserRegistrationRequest request) {
        UserRegistrationResponse response = patientServices.createNewPatient(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Patient> loginPatient(@Valid @RequestBody UserLoginRequest request) {
        Patient patient = patientServices.loginPatient(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/available-doctors")
    public ResponseEntity<List<Doctor>> getAvailableDoctors() {
        List<Doctor> availableDoctors = doctorServices.viewAvailableDoctors();
        return ResponseEntity.ok(availableDoctors);
    }

    @PostMapping("/book-appointment")
    public ResponseEntity<Appointment> bookAppointment(@RequestHeader("Authorization") String token,@Valid @RequestBody AppointmentRequest request) {

        Patient patient = patientRepository.findByEmail(patientEmail)
                .orElseThrow(() -> new InvalidPatientException("No patient found with email " + patientEmail));
        Appointment appointment = appointmentServices.createAppointment(patient, request);
        return ResponseEntity.ok(appointment);
    }




}
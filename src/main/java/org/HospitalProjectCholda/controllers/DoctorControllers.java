package org.HospitalProjectCholda.controllers;

import jakarta.validation.Valid;
import org.HospitalProjectCholda.data.models.Appointment;
import org.HospitalProjectCholda.data.models.Doctor;
import org.HospitalProjectCholda.dto.request.TreatmentRequest;
import org.HospitalProjectCholda.dto.request.UserRegistrationRequest;
import org.HospitalProjectCholda.dto.request.UserLoginRequest;
import org.HospitalProjectCholda.services.appointmentservices.AppointmentServices;
import org.HospitalProjectCholda.services.doctorservices.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorControllers {

    @Autowired
    private DoctorServices doctorServices;
    @Autowired
    private AppointmentServices appointmentServices;

    @PostMapping("/register")
    public Doctor registerDoctor(@Valid @RequestBody UserRegistrationRequest request) {
        return doctorServices.createNewDoctor(request);
    }

    @PostMapping("/login")
    public ResponseEntity<Doctor> loginDoctor(@Valid @RequestBody UserLoginRequest request) {
        Doctor doctor = doctorServices.loginDoctor(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(doctor);
//        return doctorServices.loginDoctor(request.getEmail(), request.getPassword());
    }

    @GetMapping("/view-appointment/{doctorEmail}")
    public ResponseEntity<Appointment> viewAppointment(@PathVariable String doctorEmail) {
        Appointment appointment = appointmentServices.viewSpecificAppointment(doctorEmail);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/accept-appointment/{doctorEmail}")
    public ResponseEntity<Appointment> acceptAppointment(@PathVariable String doctorEmail, @Valid @RequestBody TreatmentRequest request) {
        Appointment appointment = appointmentServices.acceptAppointment(doctorEmail, request.getTreatment());
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/cancel-appointment/{doctorEmail}")
    public ResponseEntity<String> cancelAppointment(@PathVariable String doctorEmail) {
        appointmentServices.cancelAppointment(doctorEmail);
        return ResponseEntity.ok("Appointment cancelled successfully");
    }




}

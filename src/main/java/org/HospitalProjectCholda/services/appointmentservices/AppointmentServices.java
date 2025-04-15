package org.HospitalProjectCholda.services.appointmentservices;

import org.HospitalProjectCholda.data.models.Appointment;
import org.HospitalProjectCholda.data.models.Doctor;
import org.HospitalProjectCholda.data.models.MedicalHistory;
import org.HospitalProjectCholda.data.models.Patient;
import org.HospitalProjectCholda.data.repositories.AppointmentRepository;
import org.HospitalProjectCholda.data.repositories.DoctorRepository;
import org.HospitalProjectCholda.data.repositories.MedicalHistoryRepository;
import org.HospitalProjectCholda.data.repositories.PatientRepository;
import org.HospitalProjectCholda.dto.request.AppointmentRequest;
import org.HospitalProjectCholda.exceptions.InvalidDoctorException;
import org.HospitalProjectCholda.exceptions.NoAppointmentException;
import org.HospitalProjectCholda.exceptions.NoAvailableDoctorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServices implements IAppointmentActivities{

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;
    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Appointment createAppointment(Patient patient, AppointmentRequest request){
        Doctor doctor = doctorRepository.findByEmail(request.getDoctorEmail())
                .orElseThrow(() -> new InvalidDoctorException("No Doctor found with this email: " + request.getDoctorEmail()));
        System.out.println("Doctor availability for " + doctor.getEmail() + ": " + doctor.isAvailable());
        if (!doctor.isAvailable()){
            throw new NoAvailableDoctorException("Doctor with email: " + request.getDoctorEmail() + " is busy!");
        }

//        {
//            "appointmentTime": "2025-04-09T15:30:00",
//                "description": "I have a weird headache"
//        }

        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDescription(request.getDescription());

        doctor.setAvailable(false);
        doctorRepository.save(doctor);

        return appointmentRepository.save(appointment);

    }

    @Override
    public void deleteAll() {
        doctorRepository.deleteAll();
    }

    @Override
    public Appointment viewSpecificAppointment(String doctorEmail) {
        return appointmentRepository.findByDoctor_EmailAndStatus(doctorEmail, "PENDING")
                .orElseThrow(()-> new NoAppointmentException("You have no pending appointments!"));
    }

    @Override
    public Appointment acceptAppointment(String doctorEmail, String treatment) {
        Appointment appointment = viewSpecificAppointment(doctorEmail);
        appointment.setStatus("COMPLETED");

        Patient patient = appointment.getPatient();
        Doctor doctor = appointment.getDoctor();

        MedicalHistory newEntry = new MedicalHistory();
        newEntry.setRecordDate(appointment.getAppointmentTime());
        newEntry.setDescription(appointment.getDescription());
        newEntry.setTreatment(treatment);

        MedicalHistory savedMedicalHistory = medicalHistoryRepository.save(newEntry);

        List<MedicalHistory> historyList = patient.getMedicalHistory();
        if(historyList == null){
            historyList = new ArrayList<>();
            patient.setMedicalHistory(historyList);
        }
        historyList.add(savedMedicalHistory);
        patientRepository.save(patient);

        doctor.setAvailable(true);
        doctorRepository.save(doctor);

        return appointmentRepository.save(appointment);

    }

    @Override
    public void cancelAppointment(String email) {
        Appointment appointment = viewSpecificAppointment(email);
        appointment.setStatus("CANCELLED");

        Doctor doctor = appointment.getDoctor();
        doctor.setAvailable(true);
        doctorRepository.save(doctor);

        appointmentRepository.save(appointment);

    }
}

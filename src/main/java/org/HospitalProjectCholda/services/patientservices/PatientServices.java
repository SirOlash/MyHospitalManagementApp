package org.HospitalProjectCholda.services.patientservices;

import org.HospitalProjectCholda.data.models.Patient;
import org.HospitalProjectCholda.data.repositories.PatientRepository;
import org.HospitalProjectCholda.dto.request.UserRegistrationRequest;
import org.HospitalProjectCholda.dto.response.UserRegistrationResponse;
import org.HospitalProjectCholda.exceptions.DuplicateUserException;
import org.HospitalProjectCholda.exceptions.InvalidUserNameOrPasswordException;
import org.HospitalProjectCholda.security.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServices implements IPatientActivities{

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserRegistrationResponse createNewPatient(UserRegistrationRequest request) {
        if (patientRepository.findByEmail(request.getEmail()).isPresent()){
            throw new DuplicateUserException("Patient with email " + request.getEmail() + " already exists");
        }

        Patient patient = new Patient();
        patient.setUserName(request.getUserName());
        patient.setEmail(request.getEmail());
//        patient.setPassword(request.getPassword());
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        patient.setPassword(hashedPassword);
        patientRepository.save(patient);

        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setId(patient.getId());
        response.setUsername(patient.getUserName());
        response.setEmail(request.getEmail());
        response.setMessage("Patient registered successfully");

        return response;
    }

    @Override
    public void deleteAll() {
        patientRepository.deleteAll();
    }

    @Override
    public long count() {
        return patientRepository.count();
    }

    @Override
    public Patient loginPatient(String email, String password) {
        Optional<Patient> patientOpt = patientRepository.findByEmail(email);
        if (patientOpt.isPresent()){
            Patient patient = patientOpt.get();
            if (passwordEncoder.matches(password, patient.getPassword())){
                return patient;
            }
        }
        throw new InvalidUserNameOrPasswordException("Invalid username or password");
    }

}


package org.HospitalProjectCholda.services;

import org.HospitalProjectCholda.data.models.Patient;
import org.HospitalProjectCholda.data.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServices {
    @Autowired
    private PatientRepository patientRepository;

    public Patient createNewPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deleteAll() {
        patientRepository.deleteAll();
    }

    public long count() {
        return patientRepository.count();
    }

}


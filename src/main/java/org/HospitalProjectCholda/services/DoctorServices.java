package org.HospitalProjectCholda.services;

import org.HospitalProjectCholda.data.models.Doctor;
import org.HospitalProjectCholda.data.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServices {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor createNewDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public void deleteAll() {
        doctorRepository.deleteAll();
    }

    public long count() {
        return doctorRepository.count();
    }
}

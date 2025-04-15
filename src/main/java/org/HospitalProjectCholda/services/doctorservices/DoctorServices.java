package org.HospitalProjectCholda.services.doctorservices;

import org.HospitalProjectCholda.data.models.Doctor;
import org.HospitalProjectCholda.data.repositories.DoctorRepository;
import org.HospitalProjectCholda.dto.request.UserRegistrationRequest;
import org.HospitalProjectCholda.exceptions.DuplicateUserException;
import org.HospitalProjectCholda.exceptions.InvalidUserNameOrPasswordException;
import org.HospitalProjectCholda.exceptions.NoAvailableDoctorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServices implements IDoctorActivities {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Doctor createNewDoctor(UserRegistrationRequest request){
        if (doctorRepository.findByEmail(request.getEmail()).isPresent()){
            throw new DuplicateUserException("Doctor with email " + request.getEmail() + " already exists");
        }
        Doctor doctor = new Doctor();
        doctor.setUserName(request.getUserName());
        doctor.setEmail(request.getEmail());
        String hashedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        doctor.setPassword(hashedPassword);

        return doctorRepository.save(doctor);
    }

    public void deleteAll() {
        doctorRepository.deleteAll();
    }

    public long count() {
        return doctorRepository.count();
    }

    @Override
    public Doctor loginDoctor(String email, String password) {
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            if (bCryptPasswordEncoder.matches(password,doctor.getPassword())) {
                return doctor;
            }
        }
        throw new InvalidUserNameOrPasswordException("Invalid username or password");
    }

    @Override
    public List<Doctor> viewAvailableDoctors() {
        List<Doctor> availableDoctors = doctorRepository.findByAvailableTrue();
        if(availableDoctors.isEmpty()){
            throw new NoAvailableDoctorException("No Available doctor yet!!");
        }
        return availableDoctors;
    }
}

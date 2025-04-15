package org.HospitalProjectCholda.services.doctorservices;


import org.HospitalProjectCholda.data.models.Doctor;
import org.HospitalProjectCholda.dto.request.UserRegistrationRequest;

import java.util.List;

public interface IDoctorActivities {
    Doctor createNewDoctor(UserRegistrationRequest request);
    Doctor loginDoctor(String email, String password);
    List<Doctor> viewAvailableDoctors();
}

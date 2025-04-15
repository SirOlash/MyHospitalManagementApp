package org.HospitalProjectCholda.services.patientservices;

import org.HospitalProjectCholda.data.models.Patient;
import org.HospitalProjectCholda.dto.request.UserRegistrationRequest;
import org.HospitalProjectCholda.dto.response.UserRegistrationResponse;

public interface IPatientActivities {
    UserRegistrationResponse createNewPatient(UserRegistrationRequest request);
    void deleteAll();
    long count();
    Patient loginPatient(String email, String password);
}

package org.HospitalProjectCholda.services.appointmentservices;

import org.HospitalProjectCholda.data.models.Appointment;
import org.HospitalProjectCholda.data.models.Patient;
import org.HospitalProjectCholda.dto.request.AppointmentRequest;

public interface IAppointmentActivities {
    Appointment createAppointment(Patient patient, AppointmentRequest request);
    void deleteAll();
    Appointment viewSpecificAppointment(String doctorEmail);
    Appointment acceptAppointment(String doctorEmail, String treatment);
    void cancelAppointment(String doctorEmail);
}

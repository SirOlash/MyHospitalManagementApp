package org.HospitalProjectCholda.exceptions;

public class NoAvailableDoctorException extends RuntimeException {
    public NoAvailableDoctorException(String message) {
        super(message);
    }
}

package org.HospitalProjectCholda.exceptions;

public class InvalidPatientException extends RuntimeException {
    public InvalidPatientException(String message) {
        super(message);
    }
}

package exceptions.doctorException;

import exceptions.clinicException.ClinicException;

public class DoctorNotFoundException extends ClinicException {

    public DoctorNotFoundException(String message) {
        super(message);
    }
}

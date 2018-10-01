package exceptions.workdayException;

import exceptions.clinicException.ClinicException;

public class WorkdayNotFoundException extends ClinicException {

    public WorkdayNotFoundException(String message) {
        super(message);
    }
}

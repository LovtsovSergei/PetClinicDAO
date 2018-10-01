package exceptions.workdayException;

import exceptions.clinicException.ClinicException;

public class WorkdayNotFreeException  extends ClinicException {

    public WorkdayNotFreeException(String message) {
        super(message);
    }
}

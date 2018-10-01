package exceptions.timeSlotException;

import exceptions.clinicException.ClinicException;

public class TimeSlotNotFoundException extends ClinicException {

    public TimeSlotNotFoundException(String message) {
        super(message);
    }
}

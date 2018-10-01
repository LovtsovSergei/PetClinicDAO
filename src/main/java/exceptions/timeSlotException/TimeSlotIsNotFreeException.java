package exceptions.timeSlotException;

import exceptions.clinicException.ClinicException;

public class TimeSlotIsNotFreeException extends ClinicException {

    public TimeSlotIsNotFreeException(String message) {
        super(message);
    }
}

package dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import exceptions.timeSlotException.TimeSlotNotFoundException;

import java.time.DayOfWeek;
import java.util.List;

public class Workday {

    private DayOfWeek dayOfWeek;
    @JsonProperty("office hours")
    private List<TimeSlot> timeSlots;


    public Workday(DayOfWeek dayOfWeek, List<TimeSlot> timeSlots) {
        this.dayOfWeek = dayOfWeek;
        this.timeSlots = timeSlots;
    }

    public Workday() {
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public TimeSlot getTimeSlotByStartTime(String startTime) throws TimeSlotNotFoundException {
        for (TimeSlot t : timeSlots) {
            if (t.getStartTime().toString().equals(startTime)) {
                return t;
            }
        }
        throw new TimeSlotNotFoundException("No such time");
    }

    @Override
    public String toString() {
        return "Workday{" +
                "dayOfWeek=" + dayOfWeek +
                ", timeSlots=" + timeSlots +
                '}';
    }
}

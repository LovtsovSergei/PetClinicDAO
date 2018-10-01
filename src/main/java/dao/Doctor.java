package dao;

import exceptions.workdayException.WorkdayNotFoundException;
import exceptions.workdayException.WorkdayNotFreeException;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalTime;
import java.util.*;


@XmlRootElement(name = "dao")
public class Doctor {

    private String lastName;
    private List<Workday> schedule;

    public List<Workday> getSchedule() {
        return schedule;
    }

    public Doctor(String lastName, List<Workday> schedule) {
        this.lastName = lastName;
        this.schedule = schedule;
    }

    public Doctor() {
    }

    public Workday getWorkdayByDay(String day) throws WorkdayNotFoundException {
        for (Workday w : schedule) {
            if (w.getDayOfWeek().toString().equalsIgnoreCase(day)) {
                return w;
            }
        }
        throw new WorkdayNotFoundException("No such workday");
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<TimeSlot> setDaySchedule(String schedule) {

        ArrayList<TimeSlot> timeSlots = new ArrayList<>();
        String[] scheduleArr = schedule.split("-");
        LocalTime timeBeg = LocalTime.parse(scheduleArr[0]);
        LocalTime timeEnd = LocalTime.parse(scheduleArr[1]);
        LocalTime slotTimeStart;
        LocalTime slotTimeEnd;
        while (!timeBeg.equals(timeEnd)) {
            slotTimeStart = timeBeg;
            slotTimeEnd = timeBeg.plusMinutes(30);
            timeSlots.add(new TimeSlot(slotTimeStart.toString(), slotTimeEnd.toString()));
            timeBeg = timeBeg.plusMinutes(30);
        }
        return timeSlots;
    }

    public void addWorkday(Workday workday) throws WorkdayNotFreeException {
        for (Workday w : schedule) {
            if (w.getDayOfWeek().equals(workday.getDayOfWeek())) {
                throw new WorkdayNotFreeException("The schedule already contains this workday");
            }
        }
        schedule.add(workday);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "lastName='" + lastName + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}

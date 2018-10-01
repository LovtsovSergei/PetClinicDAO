package service;

import dao.Doctor;
import dao.TimeSlot;
import exceptions.doctorException.DoctorNotFoundException;
import exceptions.timeSlotException.TimeSlotIsNotFreeException;
import exceptions.timeSlotException.TimeSlotNotFoundException;
import exceptions.workdayException.WorkdayNotFoundException;
import exceptions.workdayException.WorkdayNotFreeException;
import dao.Workday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DoctorService {


    private List<Doctor> doctors = initDoctors();

    public DoctorService() {

    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    private List<Doctor> initDoctors() {

        Doctor doc1 = new Doctor("Ivanov", new ArrayList<>());
        doc1.getSchedule().add(new Workday(DayOfWeek.MONDAY, doc1.setDaySchedule("08:00-12:30")));
        doc1.getSchedule().add(new Workday(DayOfWeek.TUESDAY, doc1.setDaySchedule("09:30-15:30")));
        doc1.getSchedule().add(new Workday(DayOfWeek.THURSDAY, doc1.setDaySchedule("14:00-18:00")));
        Doctor doc2 = new Doctor("Petrov", new ArrayList<>());
        doc2.getSchedule().add(new Workday(DayOfWeek.MONDAY, doc2.setDaySchedule("10:30-15:00")));
        doc2.getSchedule().add(new Workday(DayOfWeek.WEDNESDAY, doc2.setDaySchedule("12:30-18:30")));
        doc2.getSchedule().add(new Workday(DayOfWeek.FRIDAY, doc2.setDaySchedule("09:00-13:00")));
        Doctor doc3 = new Doctor("Sidorov", new ArrayList<>());
        doc3.getSchedule().add(new Workday(DayOfWeek.TUESDAY, doc3.setDaySchedule("12:00-19:00")));
        doc3.getSchedule().add(new Workday(DayOfWeek.WEDNESDAY, doc3.setDaySchedule("08:30-16:00")));
        doc3.getSchedule().add(new Workday(DayOfWeek.THURSDAY, doc3.setDaySchedule("08:30-15:30")));

        List<Doctor> doctorsList = new ArrayList<>();
        doctorsList.add(doc1);
        doctorsList.add(doc2);
        doctorsList.add(doc3);

        return doctorsList;
    }

    public List<String> getDoctorLastName(String lastName) throws DoctorNotFoundException {
        List<String> docsList = new ArrayList<>();

        for (Doctor doc : doctors) {
            if (doc.getLastName().equalsIgnoreCase(lastName)) {
                docsList.add(doc.getLastName());
            }
        }
        if (docsList.isEmpty()) {
            throw new DoctorNotFoundException("Doctor not found");
        }
        return docsList;
    }

    public List<String> getAllDoctorsLastNames() {
        List<String> docsList = new ArrayList<>();
        for (Doctor doc : doctors) {
            docsList.add(doc.getLastName());
        }
        return docsList;
    }

    public List<Workday> getDoctorScheduleByLastName(String lastName) throws DoctorNotFoundException {
        Doctor doc = getDoctorByLastName(lastName);
        return doc.getSchedule();
    }

    public void addWorkday(String doctorLastName, Workday workday) throws
            DoctorNotFoundException, WorkdayNotFreeException {
        Doctor doc = getDoctorByLastName(doctorLastName);
        doc.addWorkday(workday);
    }


    public DayOfWeek getDayOfWeekByDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DayOfWeek dayOfWeek = LocalDate.parse(date, dateTimeFormatter).getDayOfWeek();
        return dayOfWeek;
    }

    public Map<String, List<TimeSlot>> getFreeTimeSlotsOnCertainDate(String date) {
        Map<String, List<TimeSlot>> entries = new TreeMap<>();
        DayOfWeek day = getDayOfWeekByDate(date);
        for (Doctor d : doctors) {
            for (Workday w : d.getSchedule()) {
                if (w.getDayOfWeek().equals(day)) {
                    entries.put(d.getLastName(), getAllFreeTimeSlots(w));
                    System.out.println(d.getLastName());
                    System.out.println(w.getTimeSlots());
                }
            }
        }
        return entries;
    }

    public List<TimeSlot> getAllFreeTimeSlots(Workday workday) {
        List<TimeSlot> freeTimeSlots = new ArrayList<>();
        for (TimeSlot t : workday.getTimeSlots()) {
            if (t.getPatient() == null) {
                freeTimeSlots.add(t);
            }
        }
        return freeTimeSlots;
    }

    public void registerNewPatient(String patientLastName, String date, String time, String doctorLastName) throws
            DoctorNotFoundException, WorkdayNotFoundException, TimeSlotNotFoundException,
            TimeSlotIsNotFreeException {

        Doctor doctor = getDoctorByLastName(doctorLastName);
        String day = getDayOfWeekByDate(date).toString();
        Workday workday = doctor.getWorkdayByDay(day);
        TimeSlot timeSlot = workday.getTimeSlotByStartTime(time);

        if (timeSlot.getPatient() == null) {
            timeSlot.setPatient(patientLastName);
        } else throw new TimeSlotIsNotFreeException("This time is not free. Please, choose another time.");
    }

    public Doctor getDoctorByLastName(String lastName) throws DoctorNotFoundException {
        for (Doctor doc : doctors) {
            if (doc.getLastName().equalsIgnoreCase(lastName)) {
                return doc;
            }
        }
        throw new DoctorNotFoundException("Doctor not found");
    }
}

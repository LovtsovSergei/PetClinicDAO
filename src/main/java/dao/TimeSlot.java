package dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeSlot {

    @JsonProperty("from")
    private String startTime;
    @JsonProperty("till")
    private String endTime;
    private String patient;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }


    public TimeSlot(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeSlot() {
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", patient='" + patient + '\'' +
                '}';
    }
}

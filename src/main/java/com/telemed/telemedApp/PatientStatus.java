package com.telemed.telemedApp;

import java.util.Date;

public class PatientStatus {
    private String comment;
    private int systolic;
    private int diastolic;
    private int pulse;
    private Date date;
    private int id;
    private static int idCounter;

    public PatientStatus(Date date, int systolic, int diastolic, int pulse, String comment) {
        this.date = date;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = pulse;
        this.comment = comment;
        id = idCounter++;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

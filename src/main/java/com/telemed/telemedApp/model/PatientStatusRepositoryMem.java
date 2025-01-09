package com.telemed.telemedApp.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PatientStatusRepositoryMem {
    List<PatientStatus> patientStatus = new ArrayList<>();
/*
    public PatientStatusRepositoryMem() {
        patientStatus.add(new PatientStatus(new Date(), 120, 80, 80, "Dobro se osjecam"));
        patientStatus.add(new PatientStatus(new Date(), 150, 100, 94, "Lose se osjecam"));
        patientStatus.add(new PatientStatus(new Date(), 150, 100, 94, "Lose se osjecam"));
        patientStatus.add(new PatientStatus(new Date(), 150, 100, 94, "Lose se osjecam"));
    }
*/
    public List<PatientStatus> getPatientStatus() {
        return patientStatus;
    }

    public PatientStatus findById(int id){
        PatientStatus patientStatusToEdit = null;
        for (PatientStatus s: patientStatus) {
            if(s.getId() == id) {
                patientStatusToEdit = s;
            }
        }
        return patientStatusToEdit;
    }
}

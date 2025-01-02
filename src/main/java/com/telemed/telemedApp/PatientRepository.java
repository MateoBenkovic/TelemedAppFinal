package com.telemed.telemedApp;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepository {
    List<Patient> patientList = new ArrayList<>();


    public PatientRepository() {
        patientList.add(new Patient(1, "Ivo", "Ivić", "1990-01-01", "Muško", "Adresa 1", 10000, "Zagreb", 123456789, "ivo.ivic@example.com", "ivoivic", "lozinka123"));
        patientList.add(new Patient(2, "Ana", "Anić", "1995-05-05", "Žensko", "Adresa 2", 21000, "Split", 987654321, "ana.anic@example.com", "anaanic", "lozinka321"));
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public Patient findById(int id){
        Patient patientToEdit = null;
        for (Patient p: patientList) {
            if(p.getId() == id) {
                patientToEdit = p;
            }
        }
        return patientToEdit;
    }
}

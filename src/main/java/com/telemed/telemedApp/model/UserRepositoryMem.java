package com.telemed.telemedApp.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryMem {
    List<User> patientList = new ArrayList<>();


    public UserRepositoryMem() {
        patientList.add(new User(1, "Ivo", "Ivić", "1990-01-01", "Muško", "Adresa 1", 10000, "Zagreb", 123456789, "ivo.ivic@example.com", "ivoivic", "lozinka123"));
        patientList.add(new User(2, "Ana", "Anić", "1995-05-05", "Žensko", "Adresa 2", 21000, "Split", 987654321, "ana.anic@example.com", "anaanic", "lozinka321"));
    }

    public List<User> getPatientList() {
        return patientList;
    }

    public User findById(int id){
        User patientToEdit = null;
        for (User p: patientList) {
            if(p.getId() == id) {
                patientToEdit = p;
            }
        }
        return patientToEdit;
    }
}

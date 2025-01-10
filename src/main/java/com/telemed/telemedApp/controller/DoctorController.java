package com.telemed.telemedApp.controller;

import com.telemed.telemedApp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    UserRepository userRepositoryDB;

    @Autowired
    PatientStatusRepository patientStatusRepositoryDB;


    @GetMapping("/showPatientRecords")
    public String getPatientRecords(@RequestParam("id") int id, Model model) {
        User patient = (User) userRepositoryDB.findById(id).get();
        List<PatientStatus> records = patientStatusRepositoryDB.findByUserOrderByDateDesc(patient);
        model.addAttribute("patient", patient);
        model.addAttribute("patientStatus", records);
        return "recordsDoctorView.html";
    }

    @GetMapping("/patientsDoctorView")
    public String getAllPatients(Model model) {
        //model.addAttribute("patients", userRepositoryDB.findByType(1));
        List<Object[]> patients = userRepositoryDB.findUsersWithLatestPatientStatus();
        model.addAttribute("patients", patients);

        return "patientList.html";
    }

    @GetMapping("/newPatient")
    public String showAddPatientForm(Model model) {
        return "new_patient.html";
    }


    @GetMapping("/addNewPatient")
    public String addNewPatient(
            @RequestParam("mb") int mb,
            @RequestParam("ime") String ime,
            @RequestParam("prezime") String prezime,
            @RequestParam("datumRodjenja") String datumRodjenja,
            @RequestParam("spol") String spol,
            @RequestParam("adresa") String adresa,
            @RequestParam("pb") int pb,
            @RequestParam("grad") String grad,
            @RequestParam("kontakt") int kontakt,
            @RequestParam("email") String email,
            @RequestParam("korisnickoIme") String korisnickoIme,
            @RequestParam("lozinka") String lozinka,
            Model model) {
        User newUser = new User(mb, ime, prezime, datumRodjenja, spol, adresa, pb, grad, kontakt, email, korisnickoIme, lozinka);
        userRepositoryDB.save(newUser);

        return "redirect:/patientsDoctorView";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam("id") int id) {
        List<PatientStatus> patientStatuses = patientStatusRepositoryDB.findByUserId(id);
        patientStatusRepositoryDB.deleteAll(patientStatuses);
        userRepositoryDB.deleteById(id);
        return "redirect:/patientsDoctorView";
    }

    @GetMapping("/showPatient")
    public String showPatient(@RequestParam ("id") int id, Model model) {
        User patientToEdit = userRepositoryDB.findById(id).get();
        model.addAttribute("patient", patientToEdit);

        return "edit_patient.html";
    }

    @GetMapping("/editPatient")
    public String editPatient(@RequestParam("id") int id,
                              @RequestParam("mb") int mb,
                              @RequestParam("ime") String ime,
                              @RequestParam("prezime") String prezime,
                              @RequestParam("datumRodjenja") String datumRodjenja,
                              @RequestParam("spol") String spol,
                              @RequestParam("adresa") String adresa,
                              @RequestParam("pb") int pb,
                              @RequestParam("grad") String grad,
                              @RequestParam("kontakt") int kontakt,
                              @RequestParam("email") String email,
                              @RequestParam("korisnickoIme") String korisnickoIme,
                              @RequestParam("lozinka") String lozinka,
                              Model model) {

        User patientToEdit = userRepositoryDB.findById(id).get();
        patientToEdit.setMb(mb);
        patientToEdit.setIme(ime);
        patientToEdit.setPrezime(prezime);
        patientToEdit.setDatumRodjenja(datumRodjenja);
        patientToEdit.setSpol(spol);
        patientToEdit.setAdresa(adresa);
        patientToEdit.setPb(pb);
        patientToEdit.setGrad(grad);
        patientToEdit.setKontakt(kontakt);
        patientToEdit.setEmail(email);
        patientToEdit.setKorisnickoIme(korisnickoIme);
        patientToEdit.setLozinka(lozinka);

        userRepositoryDB.save(patientToEdit);

        return "redirect:/patientsDoctorView";
    }

}

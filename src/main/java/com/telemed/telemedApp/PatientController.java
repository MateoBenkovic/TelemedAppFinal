package com.telemed.telemedApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {
    PatientRepository patient;

    public PatientController(PatientRepository patientRepository) {
        this.patient = patientRepository;
    }



    @GetMapping("/listaPacijenata")
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patient.getPatientList());
        return "listaPacijenata.html";
    }

    @GetMapping("/addPatientForm")
    public String showAddPatientForm() {
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
            @RequestParam("lozinka") String lozinka) {
        patient.getPatientList().add(new Patient(mb, ime, prezime, datumRodjenja, spol, adresa, pb, grad, kontakt, email, korisnickoIme, lozinka));
        return "redirect:/listaPacijenata";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam("id") int id) {
        for (Patient p : patient.getPatientList()) {
            if(p.getId() == id) {
                patient.getPatientList().remove(p);
                break;
            }
        }
        return "redirect:/listaPacijenata";
    }

    @GetMapping("/showPatient")
    public String showPatient(@RequestParam ("id") int id, Model model) {
        Patient patientToEdit = patient.findById(id);
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
                              @RequestParam("lozinka") String lozinka) {

        Patient patientToEdit = patient.findById(id);
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

        return "redirect:/listaPacijenata";
    }

}

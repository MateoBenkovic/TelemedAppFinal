package com.telemed.telemedApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientStatusController {

    PatientStatusRepository patientStatus;

    public PatientStatusController(PatientStatusRepository patientStatusRepository) {
        this.patientStatus = patientStatusRepository;
    }

    @GetMapping("/patientStatus")
    public String patientStatus(Model model) {
        model.addAttribute("patientStatus", patientStatus.getPatientStatus());
        return "records.html";
    }

    @GetMapping("/patientRecords")
    public String patientRecord(Model model) {
        model.addAttribute("patientRecords", patientStatus.getPatientStatus());
        return "listaZapisa.html";
    }

    @GetMapping("/patients")
    public String showPatients(Model model) {
        return "records.html";
    }

    @GetMapping("/addNewStatus")
    public String addNewStatus(@RequestParam("systolic") int systolic,
                               @RequestParam("diastolic") int diastolic,
                               @RequestParam("pulse") int pulse,
                               @RequestParam("comment") String comment) {
        PatientStatus newPatientStatus = new PatientStatus(new Date(), systolic, diastolic, pulse, comment);
        patientStatus.getPatientStatus().add(newPatientStatus);

        return "redirect:/patientStatus";
    }

    @GetMapping("/deleteStatus")
    public String deleteStatus(@RequestParam("id") int id) {
        for (PatientStatus s : patientStatus.getPatientStatus()) {
            if (s.getId() == id) {
                patientStatus.getPatientStatus().remove(s);
                break;
            }
        }
        return "redirect:/patientStatus";
    }

    @GetMapping("/showStatus")
    public String showStatus(@RequestParam("id") int id, Model model) {
        PatientStatus patientStatusToEdit = patientStatus.findById(id);
        model.addAttribute("patientStatus", patientStatusToEdit);

        return "edit_patientStatus.html";
    }

    @GetMapping("/editPatientStatus")
    public String editPatientStatus(@RequestParam("id") int id,
                                    @RequestParam("systolic") int systolic,
                                    @RequestParam("diastolic") int diastolic,
                                    @RequestParam("pulse") int pulse,
                                    @RequestParam("comment") String comment) {

        PatientStatus patientStatusToEdit = patientStatus.findById(id);
        patientStatusToEdit.setSystolic(systolic);
        patientStatusToEdit.setDiastolic(diastolic);
        patientStatusToEdit.setPulse(pulse);
        patientStatusToEdit.setComment(comment);

        return "redirect:/patientStatus";
    }

    @GetMapping("/loginProcess")
    public String loginProcess(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {

        if (username.equals("Doktor") && password.equals("lozinka")) {
            model.addAttribute("username", username);
            return "redirect:/patients";
        } else if (username.equals("Pacijent") && password.equals("lozinka")) {
            return "redirect:/patientRecords";
        } else {
            model.addAttribute("loginMessage", "Wrong username or password");
            return "login.html";
        }
    }
}

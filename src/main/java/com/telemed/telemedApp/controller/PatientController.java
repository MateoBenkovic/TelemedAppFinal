package com.telemed.telemedApp.controller;

import com.telemed.telemedApp.model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class PatientController {

    PatientStatusRepositoryMem patientStatus;

    public PatientController(PatientStatusRepositoryMem patientStatusRepository) {
        this.patientStatus = patientStatusRepository;
    }

    @Autowired
    PatientStatusRepository patientStatusDB;

    @Autowired
    UserRepository userRepositoryDB;

    @GetMapping("/patientStatus")
    public String showTodos(Model model, HttpSession session) {

        User currentUser = (User) session.getAttribute("user");
        userRepositoryDB.findById(currentUser.getId());
        List<PatientStatus> records = patientStatusDB.findByUser(currentUser);

        model.addAttribute("patientStatus", records);

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
                               @RequestParam("comment") String comment,
                               @RequestParam("date") String dateString,
                               Model model,
                               HttpSession session) {

        LocalDate date = LocalDate.parse(dateString);

        User currentUser = (User) session.getAttribute("user");

        PatientStatus newPatientStatus = new PatientStatus(date,systolic, diastolic, pulse, comment);
        newPatientStatus.setUser(currentUser);
        patientStatusDB.save(newPatientStatus);

        return "redirect:/patientStatus";
    }

    @GetMapping("/deleteStatus")
    public String deleteStatus(@RequestParam("id") int id) {
        patientStatusDB.deleteById(id);
        return "redirect:/patientStatus";
    }

    @GetMapping("/showStatus")
    public String showStatus(@RequestParam("id") int id, Model model) {
        PatientStatus patientStatusToEdit = patientStatusDB.findById(id).get();
        model.addAttribute("patientStatus", patientStatusToEdit);

        return "edit_status.html";
    }

    @GetMapping("/editPatientStatus")
    public String editPatientStatus(@RequestParam("id") int id,
                                    @RequestParam("systolic") int systolic,
                                    @RequestParam("diastolic") int diastolic,
                                    @RequestParam("pulse") int pulse,
                                    @RequestParam("comment") String comment,
                                    @RequestParam("date") String dateString,
                                    Model model) {

        LocalDate date = LocalDate.parse(dateString);

        PatientStatus patientStatusToEdit = patientStatusDB.findById(id).get();
        patientStatusToEdit.setSystolic(systolic);
        patientStatusToEdit.setDiastolic(diastolic);
        patientStatusToEdit.setPulse(pulse);
        patientStatusToEdit.setComment(comment);
        patientStatusToEdit.setDate(date);

        patientStatusDB.save(patientStatusToEdit);

        return "redirect:/patientStatus";
    }
}

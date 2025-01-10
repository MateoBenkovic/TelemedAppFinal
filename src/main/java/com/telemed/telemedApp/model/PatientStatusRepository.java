package com.telemed.telemedApp.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientStatusRepository extends JpaRepository<PatientStatus, Integer> {

    List<PatientStatus> findByUserOrderByDateDesc(User user);
    List<PatientStatus> findByUserId(int id);

}

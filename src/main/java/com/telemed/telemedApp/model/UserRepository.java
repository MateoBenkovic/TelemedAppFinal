package com.telemed.telemedApp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {


    // ovu metodu koristimo za pronalazak entiteta kad ne želimo koristiti Optional<User> u Controlleru. Tada moramo sami napraviti metodu
    // User findById(int id);
    List<User> findByType(int type);

    @Query(value = """
    SELECT DISTINCT u.id, u.ime, u.prezime, ps.comment, ps.diastolic, ps.systolic, ps.pulse, ps.date
    FROM APP_USER u
    LEFT JOIN (
        SELECT ps1.*
        FROM PATIENT_STATUS ps1
        INNER JOIN (
            SELECT user_id, MAX(date) AS latest_date
            FROM PATIENT_STATUS
            GROUP BY user_id
        ) ps2 ON ps1.user_id = ps2.user_id AND ps1.date = ps2.latest_date
    ) ps ON u.id = ps.user_id
    WHERE u.type = 1
    ORDER BY ps.date DESC;
""", nativeQuery = true)
    List<Object[]> findUsersWithLatestPatientStatus();


}

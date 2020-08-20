/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.repositories;

import com.finalmcc.covidsystem.entities.Entry;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JESSI
 */
@Repository
public interface EntryRepository extends JpaRepository<Entry, String> {
    
    @Modifying
    @Query(value = "SELECT  entry.Id, entry.Datetime,entry.Tomorrowdate, entry.Covidweight, entry.Urgencyweight, entry.Employee, entry.Employeenote, entry.Status  FROM ((entry INNER JOIN employee ON entry.Employee=employee.Id) INNER JOIN department ON employee.Department= department.Id) WHERE department.Id =:dept", nativeQuery = true)
    List<Entry> entrybydept(@Param("dept") String dept);
 
    @Modifying
    @Query(value = "SELECT * FROM entry WHERE Status='Accept' AND Employee=:emp", nativeQuery = true)
    List<Entry> findacc(@Param("emp") String emp);
    
    @Modifying
    @Query(value = "SELECT * FROM entry WHERE Status='Reject' AND Employee=:emp", nativeQuery = true)
    List<Entry> findrej(@Param("emp") String emp);
}

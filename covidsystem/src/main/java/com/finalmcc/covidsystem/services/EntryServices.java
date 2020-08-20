/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.entities.Department;
import com.finalmcc.covidsystem.entities.Entry;
import com.finalmcc.covidsystem.repositories.DepartmentRepository;
import com.finalmcc.covidsystem.repositories.EmployeeRepository;
import com.finalmcc.covidsystem.repositories.EntryRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JESSI
 */
@Service
@Transactional
public class EntryServices {

    @Autowired
    EntryRepository entryrepo;

    @Autowired
    DepartmentRepository deptrepo;
    
    @Autowired
    EmployeeRepository emprepo;
    
    public List<Entry> entrybydept(String dept) {
        return entryrepo.entrybydept(dept);
    }
    
    public List<Entry> entrybyemp(String emp){
        return (List<Entry>) emprepo.findById(emp).get().getEntryCollection();
    }
    
    public List<Entry> findacc(String emp){
        return entryrepo.findacc(emp);
    }
    public List<Entry> findrej(String emp){
        return entryrepo.findrej(emp);
    }
    

}

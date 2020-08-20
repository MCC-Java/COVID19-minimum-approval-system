/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.controllers;

import com.finalmcc.covidsystem.services.DateServices;
import com.finalmcc.covidsystem.services.DepartmentServices;
import com.finalmcc.covidsystem.services.EmployeeServices;
import com.finalmcc.covidsystem.services.EntryServices;
import com.finalmcc.covidsystem.services.GroupCodeServices;
import com.finalmcc.covidsystem.services.QuestionServices;
import com.finalmcc.covidsystem.services.RapidResultServices;
import com.finalmcc.covidsystem.services.RequestQuotaServices;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author JESSI
 */

@Controller
public class EmpController {
    
    @Autowired
    DateServices dateservices;

    @Autowired
    EmployeeServices empservices;

    @Autowired
    RapidResultServices rrservices;

    @Autowired
    DepartmentServices deptservices;

    @Autowired
    EntryServices entryservices;

    @Autowired
    QuestionServices questionservices;

    @Autowired
    GroupCodeServices groupservices;

    @Autowired
    RequestQuotaServices reqservices;

    @RequestMapping("/employee/{user}")
    public String tohomepage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        return "emp";
    }
    
     @RequestMapping("/employee/answer/{user}")
    public String toanswerpage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        return "emppertanyaan";
    }
    
     @RequestMapping("/employee/history/{user}")
    public String toriwayatpage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
         model.addAttribute("active", entryservices.findacc(user));
        model.addAttribute("reject", entryservices.findrej(user));
        return "empriwayat";
    }
}

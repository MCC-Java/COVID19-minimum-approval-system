/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.controllers;

import com.finalmcc.covidsystem.entities.RequestQuota;
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
public class ManajerController {

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

    @RequestMapping("/manajer/{user}")
    public String tohomepage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        return "manajer";
    }

    @RequestMapping("/manajer/answer/{user}")
    public String toanswerpage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        return "manajerpertanyaan";
    }

    @RequestMapping("/manajer/history/{user}")
    public String tohistorypage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("active", entryservices.findacc(user));
        model.addAttribute("reject", entryservices.findrej(user));
        return "manajerriwayat";
    }

    @RequestMapping("/manajer/department/{user}")
    public String todepartmentpage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        String iddept = empservices.getbyid(user).getDepartment().getId();
        model.addAttribute("dept", deptservices.getbyid(iddept));
        model.addAttribute("emp", empservices.getbydept(iddept));
        model.addAttribute("entry", entryservices.entrybydept(iddept));
        model.addAttribute("count", empservices.countbydept(iddept));
        model.addAttribute("request", new RequestQuota());
        return "manajerdepartment";
    }

    @PostMapping("/manajer/saverequest/{user}")
    public String saverequest(@PathVariable(name = "user") String user, RequestQuota req) {
        
        return "redirect:/manajer/department/" + user;
    }
    
}

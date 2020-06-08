package com.sandeepmane.awssessdkservice.controller;

import com.sandeepmane.awssessdkservice.model.Email;
import com.sandeepmane.awssessdkservice.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping(path = "/send")
    public String sendEmail(Email email) {

        if (emailService.sendEmail(email)) {
            return "Email sent Successfully";
        } else {
            return "Email Failed!!!!!!!!";
        }

    }

}
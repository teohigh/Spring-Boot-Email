package com.tainn.controller;

import com.tainn.model.User;
import com.tainn.service.EmailService;
import com.tainn.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class EmailController {
    EmailService emailService;
    UserService userService;

    @PostMapping("/user")
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/send-email")
    public String sendEmailToUser(@RequestParam int id) {
        String subject = "Your Reservation Is Confirmed!";
        User user = userService.findById(id);
        String body = "Dear " + user.getName() + ",\n\n" +
                "Your reservation is confirmed. We're thrilled to host you for a memorable stay. If you have any questions or special requests, feel free to reach out.\n\n" +
                "We look forward to providing you with an exceptional experience. Safe travels!\n\n" +
                "Warm regards,\n" +
                "HUTA Homestay";
        emailService.sendSimpleEmail(user.getEmail(), body, subject);
        return "Email sent successfully";
    }
}

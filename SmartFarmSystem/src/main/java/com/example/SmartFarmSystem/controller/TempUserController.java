//package com.example.SmartFarmSystem.controller;
//
//import com.example.SmartFarmSystem.domain.entity.User;
//import com.example.SmartFarmSystem.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.SessionAttribute;
//
//@RestController
//@RequestMapping("/api/user")
//public class TempUserController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/info")
//    public ResponseEntity<User> getUserInfo(@SessionAttribute(name = "userId", required = false) Long userId) {
//        if (userId != null) {
//            User user = userService.getLoginUserById(userId);
//            if (user != null) {
//                return ResponseEntity.ok(user);
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//}

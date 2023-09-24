//package com.example.SmartFarmSystem.controller;
//
//import com.example.SmartFarmSystem.domain.Member;
//import com.example.SmartFarmSystem.domain.SmartFarm;
//import com.example.SmartFarmSystem.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class MemberController {
//
//    private MemberService memberService;
//
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//    @PostMapping("/member/register")
//    @ResponseBody
//    public void registerMember(@RequestBody Member member) throws Exception {
//        System.out.println("INFO  member registered!");
//        memberService.registerMember(member);
//    }
//
//    @PostMapping("/member/login")
//    @ResponseBody
//    public ResponseEntity<String> loginMember(@RequestBody Member member) {
//        boolean isLoggedIn = memberService.loginMember(member.getmid(), member.getmpw());
////        boolean isLoggedIn = true;
//        if (isLoggedIn) {
//            System.out.println("INFO  Member Login Success");
//            return new ResponseEntity<>("Login Success", HttpStatus.OK);
//        } else {
//            System.out.println("INFO  Member Login Failure");
//            return new ResponseEntity<>("Login Failure", HttpStatus.UNAUTHORIZED);
//        }
//    }
//}
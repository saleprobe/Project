package com.example.SmartFarmSystem.controller;

import com.example.SmartFarmSystem.domain.UserRole;
import com.example.SmartFarmSystem.domain.dto.JoinRequest;
import com.example.SmartFarmSystem.domain.dto.LoginRequest;
import com.example.SmartFarmSystem.domain.entity.User;
import com.example.SmartFarmSystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user_related")
public class SessionLoginController {

    private final UserService userService;

    @GetMapping(value = {"", "/"})
    public String home(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        User loginUser = userService.getLoginUserById(userId);

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.getNickname());
        }

        return "loginhome";
    }

    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        model.addAttribute("joinRequest", new JoinRequest());
        return "join";
    }

    @PostMapping("/join")  // 회원가입 API
    public ResponseEntity<String> join(@Valid @ModelAttribute JoinRequest joinRequest, BindingResult bindingResult, Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        // loginId 중복 체크
        if (userService.checkLoginIdDuplicate(joinRequest.getLoginId())) {
            bindingResult.addError(new FieldError("joinRequest", "loginId", "로그인 아이디가 중복됩니다."));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 아이디가 중복됩니다.");
        }
//        // 닉네임 중복 체크
//        if (userService.checkNicknameDuplicate(joinRequest.getNickname())) {
//            bindingResult.addError(new FieldError("joinRequest", "nickname", "닉네임이 중복됩니다."));
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("닉네임이 중복됩니다.");
//        }
        // password와 passwordCheck가 같은지 체크
        if (!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
            bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "바밀번호가 일치하지 않습니다."));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력값이 올바르지 않습니다.");
        }

        userService.join(joinRequest);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")  // 로그인 API
    public ResponseEntity<String> login(@ModelAttribute LoginRequest loginRequest, BindingResult bindingResult,
                                        HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        User user = userService.login(loginRequest);

        // 로그인 아이디나 비밀번호가 틀린 경우
        if (user == null) {
            bindingResult.reject("loginFail", "로그인 아이디 또는 비밀번호가 틀렸습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("입력된 값이 올바르지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("입력된 값이 올바르지 않습니다.");
        }

        // 로그인 성공 => 세션 생성

        // 세션을 생성하기 전에 기존의 세션 파기
        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);  // Session이 없으면 생성
        // 세션에 userId를 넣어줌
        session.setAttribute("userId", user.getId());
        session.setMaxInactiveInterval(43200); // Session이 12시간동안 유지

        return ResponseEntity.status(HttpStatus.OK)
                .body("로그인 성공");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        HttpSession session = request.getSession(false);  // Session이 없으면 null return
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/user_related";
    }

    @GetMapping("/info")
    public String userInfo(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        User loginUser = userService.getLoginUserById(userId);

        if (loginUser == null) {
            return "redirect:/user_related/login";
        }

        model.addAttribute("user", loginUser);
        return "info";
    }

    @GetMapping("/info_sf_id")
    public String sfidInfo(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        User loginUser = userService.getLoginUserById(userId);

        if (loginUser == null) {
            return "redirect:/user_related/login";
        }

        model.addAttribute("user", loginUser);
        return "info_sf_id";
    }

    @GetMapping("/info_crop")
    public String cropInfo(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");
        System.out.println("/info_crop    userId: " + userId);
        User loginUser = userService.getLoginUserById(userId);
        System.out.println("/info_crop    loginUser: " + loginUser);

        if (loginUser == null) {
            return "redirect:/user_related/login";
        }

        model.addAttribute("user", loginUser);
        return "info_crop";
    }

    @GetMapping("/admin")
    public String adminPage(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        User loginUser = userService.getLoginUserById(userId);

        if (loginUser == null) {
            return "redirect:/user_related/login";
        }

        if (!loginUser.getRole().equals(UserRole.ADMIN)) {
            return "redirect:/user_related";
        }

        return "admin";
    }

    @PostMapping("/inject_sf_id")  // 스마트팜 고유번호 등록 API
    public ResponseEntity<String> injectSfId(
            @SessionAttribute(name = "userId", required = false) Long userId,
            @RequestParam("user_sf_id") int userSfId, Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");
        System.out.println(userId);
        System.out.println(userSfId);
        if (userId == null) {
            // 세션이 유효하지 않은 경우
            System.out.println("INFO    ERROR");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("유효하지 않은 세션");
        }

        // 로그인된 사용자의 user_sf_id 값을 업데이트
        boolean updateSuccess = userService.updateUserSfId(userId, userSfId);
        System.out.println("INFO    " + updateSuccess);
        return ResponseEntity.status(HttpStatus.OK)
                .body("스마트팜 고유번호 등록 성공");
    }

    @PostMapping("/inject_crop")  // 작물 등록 API
    public ResponseEntity<String> registerCrop(
            @SessionAttribute(name = "userId", required = false) Long userId,
            @RequestParam("cropname") String cropname,
            @RequestParam("period") short period, Model model) {
        model.addAttribute("loginType", "user_related");
        model.addAttribute("pageName", "마이 페이지");

        if (userId == null) {
            // 세션이 유효하지 않은 경우
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("유효하지 않은 세션");
        }

        // 로그인된 사용자의 cropname, period 값을 업데이트
        boolean updateSuccess = userService.updateUserCrop(userId, cropname, period);
        System.out.println("INFO    " + updateSuccess);
        return ResponseEntity.status(HttpStatus.OK)
                .body("작물 등록 성공");
    }

    @GetMapping("/get_session_id")
    public ResponseEntity<String> getSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션이 없을 경우 null을 반환

        if (session != null) {
            String sessionId = session.getId();
            return ResponseEntity.ok("Session ID: " + sessionId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Session not found.");
        }
    }

    @GetMapping("/get_nickname")
    public ResponseEntity<String> getLoggedInUserNickname(@SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId != null) {
            User loginUser = userService.getLoginUserById(userId);
            if (loginUser != null) {
                String nickname = loginUser.getNickname();
                return ResponseEntity.ok(nickname); // 닉네임을 그대로 반환
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자를 불러올 수 없습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인되지 않은 사용자입니다.");
        }
    }
}

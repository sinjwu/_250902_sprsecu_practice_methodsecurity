package com.example._250902_sprsecu_practice_methodsecurity.security;

import com.example._250902_sprsecu_practice_methodsecurity.model.Department;
import com.example._250902_sprsecu_practice_methodsecurity.model.Role;
import com.example._250902_sprsecu_practice_methodsecurity.model.User;
import com.example._250902_sprsecu_practice_methodsecurity.repository.PostRepository;
import com.example._250902_sprsecu_practice_methodsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component("customSecurity")
@RequiredArgsConstructor
@Slf4j
public class CustomSecurityExpressions {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    // 업무시간 9 ~ 17
    public boolean isBusinessHours() {
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);
        boolean result = now.isAfter(start) && now.isBefore(end);
        log.info("\uD83D\uDD50 업무 시간 체크: " + now + " -> " + result);
        return result;
    }
    public boolean isActiveUser(Authentication authentication) {
        if (authentication.getPrincipal() instanceof User user) {
            return user.isEnabled() && user.isAccountNonExpired();
        }
        return false;
    }
    public boolean isDepartmentMember(Department department, Authentication authentication) {
        if (authentication.getPrincipal() instanceof User user) {
            return user.getDepartment() == department;
        }
        return false;
    }
    public boolean isManagerOrHigher(Authentication authentication) {
        if (authentication.getPrincipal() instanceof User user) {
            return user.getRole() == Role.MANAGER || user.getRole() == Role.ADMIN;
        }
        return false;
    }
    public boolean hasMinimumPosts(int minimumCount, Authentication authentication) {
        if (authentication.getPrincipal() instanceof User user) {
            int postCount = postRepository.findByAuthor(user).size();
            return postCount >= minimumCount;
        }
        return false;
    }
}

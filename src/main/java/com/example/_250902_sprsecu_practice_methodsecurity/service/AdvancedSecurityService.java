package com.example._250902_sprsecu_practice_methodsecurity.service;

import com.example._250902_sprsecu_practice_methodsecurity.model.User;
import com.example._250902_sprsecu_practice_methodsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdvancedSecurityService {
    private final UserRepository userRepository;
    @PreAuthorize("@customerSecurity.isBusinessHours() or hasRole('ADMIN')")
    public void performSensitiveOperation() {
        log.info("⚠\uFE0F 민감한 작업 실행 - 업무 시간 또는 관리자");
    }
    @PreAuthorize("@customSecurity.isDepartmentMember(T(com.example._250902_sprsecu_practice_methodsecurity.model.Department).HR, authentication")
    public List<User> getEmployeeRecords() {
        log.info("\uD83D\uDC65 직원 기록 조회 - HR 부서원만");
        return userRepository.findAll();
    }
    @PreAuthorize("@customSecurity.isManagerOrHigher(authentication)")
    public void approveRequest(Long requestId) {
        log.info("✅ 요청 승인 - 매니저 이상");
    }
    @PreAuthorize("@customSecurity.isActiveUser(authentication) and "
            + "@customSecurity.hasMinimumPosts(5, authentication)"
    )
    public void accessPremiumFeature() {
        log.info("\uD83D\uDC8E 프리미엄 기능 - 활성 사용자이면서 게시글 5개 이상");
    }
    @PreAuthorize("@customSecurity.isBusinessHours() and "
            + "@customSecurity.isDepartmentMember(T(com.example._250902_sprsecu_practice_methodsecurity.model.Department).FINANCE, authentication) and "
            + "@customSecurity.isManagerOrHigher(authentication)"
    )
    public void processPayroll() {
        log.info("\uD83D\uDCB0 급여 처리 - 업무시간 + 재무부서 + 매니저 이상");
    }
}

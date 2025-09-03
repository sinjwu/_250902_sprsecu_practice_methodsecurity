package com.example._250902_sprsecu_practice_methodsecurity.dto;

import com.example._250902_sprsecu_practice_methodsecurity.model.Department;
import com.example._250902_sprsecu_practice_methodsecurity.model.Role;
import com.example._250902_sprsecu_practice_methodsecurity.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private Department department;
    private boolean enabled;
    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .department(user.getDepartment())
                .enabled(user.isEnabled())
                .build();
    }
}

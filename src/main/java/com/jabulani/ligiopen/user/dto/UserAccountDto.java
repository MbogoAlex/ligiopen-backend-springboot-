package com.jabulani.ligiopen.user.dto;

import com.jabulani.ligiopen.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountDto {
    private Integer id;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
    private Boolean archived;
    private LocalDateTime archivedAt;
}
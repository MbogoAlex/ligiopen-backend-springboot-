package com.jabulani.ligiopen.user.dto.mapper;

import com.jabulani.ligiopen.user.dto.UserAccountDto;
import com.jabulani.ligiopen.user.entity.UserAccount;

public class UserAccountMapper {
    public UserAccountDto toUserDto(UserAccount userAccount) {
        return UserAccountDto.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .email(userAccount.getEmail())
                .role(userAccount.getRole())
                .createdAt(userAccount.getCreatedAt())
                .archived(userAccount.getArchived())
                .archivedAt(userAccount.getArchivedAt())
                .build();
    }
}

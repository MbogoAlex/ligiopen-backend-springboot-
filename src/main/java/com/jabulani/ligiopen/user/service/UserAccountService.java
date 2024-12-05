package com.jabulani.ligiopen.user.service;

import com.jabulani.ligiopen.user.dto.*;

import java.util.List;

public interface UserAccountService {
    UserAccountDto createUserAccount(RegisterUserDto userAccount);
    UserAccountDto updateUserAccount(UpdateUserDto userAccount);
    UserAccountDto changeUserPassword(PasswordChangeDto passwordChangeDto);
    UserAccountDto getUserAccountById(Integer userId);
    UserAccountDto getUserAccountByEmail(String email);
    List<UserAccountDto> getAllUsers();
}

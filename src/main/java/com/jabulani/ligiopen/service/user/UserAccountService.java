package com.jabulani.ligiopen.service.user;

import com.jabulani.ligiopen.model.user.dto.PasswordChangeDto;
import com.jabulani.ligiopen.model.user.dto.RegisterUserDto;
import com.jabulani.ligiopen.model.user.dto.UpdateUserDto;
import com.jabulani.ligiopen.model.user.dto.UserAccountDto;

import java.util.List;

public interface UserAccountService {
    UserAccountDto createUserAccount(RegisterUserDto userAccount);
    UserAccountDto updateUserAccount(UpdateUserDto userAccount);
    UserAccountDto changeUserPassword(PasswordChangeDto passwordChangeDto);
    UserAccountDto getUserAccountById(Integer userId);
    UserAccountDto getUserAccountByEmail(String email);
    Boolean existsByEmail(String email);
    List<UserAccountDto> getAllUsers();
}

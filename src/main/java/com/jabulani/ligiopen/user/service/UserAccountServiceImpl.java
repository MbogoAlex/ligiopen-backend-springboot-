package com.jabulani.ligiopen.user.service;

import com.jabulani.ligiopen.user.dao.UserAccountDao;
import com.jabulani.ligiopen.user.dto.*;
import com.jabulani.ligiopen.user.dto.mapper.UserAccountMapper;
import com.jabulani.ligiopen.user.entity.Role;
import com.jabulani.ligiopen.user.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountServiceImpl implements UserAccountService{
    private final UserAccountMapper userAccountMapper = new UserAccountMapper();
    private final UserAccountDao userAccountDao;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserAccountServiceImpl(
            UserAccountDao userAccountDao,
            PasswordEncoder passwordEncoder
    ) {
        this.userAccountDao = userAccountDao;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    @Override
    public UserAccountDto createUserAccount(RegisterUserDto userRegDetails) {
        UserAccount userAccount = UserAccount.builder()
                .username(userRegDetails.getUsername())
                .email(userRegDetails.getEmail())
                .password(passwordEncoder.encode(userRegDetails.getPassword()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now().plusHours(3))
                .build();
        return userAccountMapper.toUserDto(userAccountDao.createUserAccount(userAccount));
    }
    @Transactional
    @Override
    public UserAccountDto updateUserAccount(UpdateUserDto updatedUserDetails) {
        UserAccount userAccount = userAccountDao.getUserAccountById(updatedUserDetails.getUserId());
        if(!userAccount.getUsername().equalsIgnoreCase(updatedUserDetails.getUsername())) {
            userAccount.setUsername(updatedUserDetails.getUsername());
        }

        if(!userAccount.getEmail().equalsIgnoreCase(updatedUserDetails.getEmail())) {
            userAccount.setUsername(updatedUserDetails.getUsername());
        }
        return userAccountMapper.toUserDto(userAccountDao.updateUserAccount(userAccount));
    }
    @Transactional
    @Override
    public UserAccountDto changeUserPassword(PasswordChangeDto passwordChangeDto) {
        UserAccount userAccount = userAccountDao.getUserAccountById(passwordChangeDto.getUserId());
        userAccount.setPassword(passwordEncoder.encode(passwordChangeDto.getPassword()));
        return userAccountMapper.toUserDto(userAccountDao.updateUserAccount(userAccount));
    }

    @Override
    public UserAccountDto getUserAccountById(Integer userId) {
        return userAccountMapper.toUserDto(userAccountDao.getUserAccountById(userId));
    }

    @Override
    public UserAccountDto getUserAccountByEmail(String email) {
        return userAccountMapper.toUserDto(userAccountDao.getUserAccountByEmail(email));
    }

    @Override
    public List<UserAccountDto> getAllUsers() {
        return userAccountDao.getAllUsers().stream().map(userAccountMapper::toUserDto).collect(Collectors.toList());
    }
}

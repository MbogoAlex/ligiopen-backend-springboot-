package com.jabulani.ligiopen.dao.user;

import com.jabulani.ligiopen.model.user.UserAccount;
import java.util.List;
public interface UserAccountDao {
    UserAccount createUserAccount(UserAccount userAccount);
    UserAccount updateUserAccount(UserAccount userAccount);
    UserAccount getUserAccountById(Integer userId);
    UserAccount getUserAccountByEmail(String email);
    Boolean existsByEmail(String email);
    List<UserAccount> getAllUsers();
}

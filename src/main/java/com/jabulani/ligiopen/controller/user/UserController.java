package com.jabulani.ligiopen.controller.user;

import com.jabulani.ligiopen.config.response.Response;
import com.jabulani.ligiopen.model.user.dto.SetContentAdminDto;
import com.jabulani.ligiopen.model.user.dto.SetSuperAdminDto;
import com.jabulani.ligiopen.model.user.dto.SetTeamAdminDto;
import com.jabulani.ligiopen.model.user.dto.UserAccountDto;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<Response> setSuperAdmin(SetSuperAdminDto setSuperAdminDto);
    ResponseEntity<Response> setContentAdmin(SetContentAdminDto setContentAdminDto);
    ResponseEntity<Response> setTeamAdmin(SetTeamAdminDto setTeamAdminDto);
}

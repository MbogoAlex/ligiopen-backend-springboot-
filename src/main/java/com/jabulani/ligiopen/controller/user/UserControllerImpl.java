package com.jabulani.ligiopen.controller.user;

import com.jabulani.ligiopen.config.response.BuildResponse;
import com.jabulani.ligiopen.config.response.Response;
import com.jabulani.ligiopen.model.user.dto.SetContentAdminDto;
import com.jabulani.ligiopen.model.user.dto.SetSuperAdminDto;
import com.jabulani.ligiopen.model.user.dto.SetTeamAdminDto;
import com.jabulani.ligiopen.service.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class UserControllerImpl implements UserController{

    private final BuildResponse buildResponse;
    private final UserAccountService userAccountService;

    @Autowired
    public UserControllerImpl(BuildResponse buildResponse, UserAccountService userAccountService) {
        this.buildResponse = buildResponse;
        this.userAccountService = userAccountService;
    }

    @PutMapping("user/super-admin")
    @Override
    public ResponseEntity<Response> setSuperAdmin(@RequestBody SetSuperAdminDto setSuperAdminDto) {
        return buildResponse.createResponse("role", userAccountService.setSuperAdmin(setSuperAdminDto), "Super admin set", HttpStatus.CREATED);
    }

    @PutMapping("user/content-admin")
    @Override
    public ResponseEntity<Response> setContentAdmin(@RequestBody SetContentAdminDto setContentAdminDto) {
        return buildResponse.createResponse("role", userAccountService.setContentAdmin(setContentAdminDto), "Content admin set", HttpStatus.CREATED);
    }

    @PutMapping("user/team-admin")
    @Override
    public ResponseEntity<Response> setTeamAdmin(@RequestBody SetTeamAdminDto setTeamAdminDto) {
        return buildResponse.createResponse("role", userAccountService.setTeamAdmin(setTeamAdminDto), "Team admin set", HttpStatus.CREATED);
    }
}

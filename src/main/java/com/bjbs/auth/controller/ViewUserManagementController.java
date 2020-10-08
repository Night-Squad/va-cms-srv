package com.bjbs.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjbs.auth.dtos.ViewUserManagementDTO;
import com.bjbs.auth.models.ViewUserManagement;
import com.io.iona.springboot.controllers.HibernateCRUDController;

@RestController
@RequestMapping("/api/users_management_data")
public class ViewUserManagementController extends HibernateCRUDController<ViewUserManagement, ViewUserManagementDTO> {

}

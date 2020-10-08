package com.bjbs.auth.olo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjbs.auth.models.Role;
import com.bjbs.auth.olo.RoleOLO;
import com.io.iona.springboot.controllers.HibernateOptionListController;

@RestController
@RequestMapping("/ol/role")
public class RoleOLOController extends HibernateOptionListController<Role, RoleOLO>{

}

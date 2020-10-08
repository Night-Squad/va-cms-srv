package com.bjbs.auth.olo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjbs.auth.models.Users;
import com.bjbs.auth.olo.UsersOLO;
import com.io.iona.springboot.controllers.HibernateOptionListController;

@RestController
@RequestMapping("/ol/users")
public class UsersOLOController extends HibernateOptionListController<Users,UsersOLO>{

}

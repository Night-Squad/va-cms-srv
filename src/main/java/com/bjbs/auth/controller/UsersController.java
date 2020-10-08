package com.bjbs.auth.controller;

//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjbs.auth.dtos.UsersDTO;
import com.bjbs.auth.models.Users;
import com.bjbs.auth.utility.HashUtil.SHA_256;
import com.io.iona.springboot.actionflows.custom.CustomBeforeUpdate;
import com.io.iona.springboot.controllers.HibernateCRUDController;
import com.io.iona.springboot.sources.HibernateDataSource;
import com.io.iona.springboot.sources.HibernateDataUtility;

@RestController
@RequestMapping("/api/users")
public class UsersController extends HibernateCRUDController<Users, UsersDTO> implements CustomBeforeUpdate<Users, UsersDTO> {
	
	// this api used by change password
	@Override
	public void beforeUpdate(HibernateDataUtility dataUtility, HibernateDataSource<Users, UsersDTO> dataSource) throws Exception {
		Users user = dataSource.getDataModel();
        user.setPassword(SHA_256.digestAsHex(user.getPassword()));
        // I don't know if the datasource is mutable or not and if the below code is necessary or not
        // So I'll just implement this just to make sure that the value is replaced
        dataSource.setDataModel(user);
    }

}

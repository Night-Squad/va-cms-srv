package com.bjbs.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjbs.auth.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}

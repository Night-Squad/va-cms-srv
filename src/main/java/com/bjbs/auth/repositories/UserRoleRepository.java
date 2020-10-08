package com.bjbs.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjbs.auth.models.UserRole;

/**
 * Created By Fega Eka
 */

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
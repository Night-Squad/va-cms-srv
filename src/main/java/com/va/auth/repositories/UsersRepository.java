package com.va.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.va.auth.models.Users;

/**
 * Created By Aristo Pacitra
 */

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
@Query(value = "SELECT * FROM users u WHERE u.name = ?1",nativeQuery = true)
public Users findUserByUsername(String username);

@Modifying
@Query(value = "UPDATE users set password = ?1 where id = ?2",nativeQuery = true)
public int updatePassword(String password, long UserId);
}
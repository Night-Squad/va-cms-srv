package com.bjbs.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bjbs.auth.models.Users;

/**
 * Created By Aristo Pacitra
 */

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
@Query(value = "SELECT * FROM Users u WHERE u.user_name = ?1",nativeQuery = true)
public Users findUserByUsername(String username);

@Modifying
@Query(value = "UPDATE Users set password = ?1 where user_id = ?2",nativeQuery = true)
public int updatePassword(String password, long UserId);
}
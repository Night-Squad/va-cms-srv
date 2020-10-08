package com.bjbs.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bjbs.auth.models.ViewUserManagement;

public interface ViewUserManagementRepository extends JpaRepository<ViewUserManagement, String> {
	
	@Query(value = "SELECT * FROM view_user_management vum WHERE vum.user_id = ?1", nativeQuery = true)
	public ViewUserManagement findUserManagementByUserId(long userId);

}

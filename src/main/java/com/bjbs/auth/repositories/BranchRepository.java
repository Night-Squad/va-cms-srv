package com.bjbs.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bjbs.auth.models.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
	
	@Query(value = "SELECT * FROM branch b WHERE b.branch_code = ?1", nativeQuery = true)
	public Branch findBranchByBranchCode(String branchCode);
	
}

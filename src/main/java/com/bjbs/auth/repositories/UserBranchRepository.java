package com.bjbs.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjbs.auth.models.UserBranch;

public interface UserBranchRepository extends JpaRepository<UserBranch, Long> {

}

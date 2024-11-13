package com.va.corporate.srv.service;

import com.va.corporate.srv.domain.LoginAttempDomain;
import com.va.corporate.srv.repository.LoginAttemptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LoginService {

    private final LoginAttemptRepository repository;

    public LoginService(LoginAttemptRepository repository) {
        this.repository = repository;
    }

//    public List<LoginAttempDomain> findRecentLoginAttempts(String email) {
//        return repository.findRecent(email);
//    }
}

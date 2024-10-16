package com.server.auth.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.auth.dtos.CustomUser;
import com.server.auth.models.Users;
import com.server.auth.repositories.UsersRepository;

/**
 * Created By Aristo Pacitra
 */

@Service 
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService  {
	@Autowired
	private UsersRepository UsersRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		int applicationId = 0;
		Users users = UsersRepository.findUserByUsername(username);

		System.out.println("Masuk Lewat Sini");


//	      this.titleCode = titleCode;
//	        this.refDivisionId = refDivisionId;
//	        this.divisionName = divisionName;
//	        this.refLembagaId = refLembagaId;
//	        this.namaLembaga = namaLembaga;

		if(users != null) {
			System.out.println("here...");
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_ADMIN");

			System.out.println("here 2...");
			System.out.println("users.getPassword(): "+users.getPassword());
			System.out.println("encoder.encode(users.getPassword()) : "+encoder.encode(users.getPassword()));
			return new CustomUser(users.getName(), encoder.encode(users.getPassword()), grantedAuthorities,
					users.getId(), users.getName(), users.getPassword());
		}

		throw new UsernameNotFoundException("Username: " + username + " not found");
	}

}
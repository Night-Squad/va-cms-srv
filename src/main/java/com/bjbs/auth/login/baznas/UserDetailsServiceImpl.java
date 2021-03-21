package com.bjbs.auth.login.baznas;

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

import com.bjbs.auth.dtos.CustomUser;
import com.bjbs.auth.models.Users;
import com.bjbs.auth.repositories.UsersRepository;

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
		
//	      this.titleCode = titleCode;
//	        this.refDivisionId = refDivisionId;
//	        this.divisionName = divisionName;
//	        this.refLembagaId = refLembagaId;
//	        this.namaLembaga = namaLembaga;

		if(users != null) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
			return new CustomUser(users.getUserName(), encoder.encode(users.getPassword()), users.getUserRealName(), grantedAuthorities,
					users.getUserId(), applicationId, users.getRefTitleId(), users.getTitleCode(), users.getRefDivisionId(),
					users.getDivisionName(), users.getRefLembagaId(), users.getNamaLembaga());
		} 
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
	
}
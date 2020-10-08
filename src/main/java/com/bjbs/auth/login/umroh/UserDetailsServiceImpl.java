package com.bjbs.auth.login.umroh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.bjbs.auth.dtos.RoleMenuDTO;
import com.bjbs.auth.models.RoleMenu;
import com.bjbs.auth.models.UserRole;
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

		if(users != null) {
			Map<String, List<RoleMenuDTO>> userRoleMenu = new HashMap<>();
			for	(UserRole userRole: users.getUserRoles()) {
				List<RoleMenuDTO> listRoleMenuDTO = new ArrayList<>();
				for (RoleMenu roleMenus: userRole.getRole().getRoleMenus()) {
					RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
					roleMenuDTO.setRoleMenuId(roleMenus.getRoleMenuId());
					roleMenuDTO.setMenuId(roleMenus.getMenu().getMenuId());
					roleMenuDTO.setMenuName(roleMenus.getMenu().getMenuName());
					roleMenuDTO.setMenuDescription(roleMenus.getMenu().getMenuDescription());
					roleMenuDTO.setMenuCategory(roleMenus.getMenu().getMenuCategory());
					roleMenuDTO.setRoleId(roleMenus.getRole().getRoleId());
					roleMenuDTO.setRoleName(roleMenus.getRole().getRoleName());
					roleMenuDTO.setRoleDescription(roleMenus.getRole().getRoleDescription());
					listRoleMenuDTO.add(roleMenuDTO) ;
				}
				userRoleMenu.put(userRole.getRole().getRoleName(), listRoleMenuDTO);
			}
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
			return new CustomUser(users.getUserName(), encoder.encode(users.getPassword()), users.getUserRealName(), grantedAuthorities, 
					users.getUserId(), applicationId, userRoleMenu);
		} 
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
	
}
package com.va.auth.repositories.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.va.auth.controller.UsersController;
import com.va.auth.dtos.ChangePasswordDTO;
import com.va.auth.dtos.Response;
import com.va.auth.models.Users;
import com.va.auth.repositories.UsersRepository;
import com.va.auth.utility.HashUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/users_management")
public class UsersManagementRepositoryController {

	@Autowired
    UsersController usersController;


	@Autowired
    UsersRepository usersRepository;


	@GetMapping("/get_user_management")
	public Map<String, Object> readAll(@RequestParam long userId, HttpServletRequest request) {
		
		String remoteAddr = "";
		Map<String, Object> result = new HashMap<String, Object>();
		
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        result.put("userIpAddress", remoteAddr);
        
		return result;
	}

	ModelMapper mapper = new ModelMapper();
	ObjectMapper mapperObj = new ObjectMapper();

//	@Transactional
//	@PostMapping("/create_user_management")
//	public Object create(@RequestHeader(value="user-code") String userCode,
//			@RequestBody CreateUserDTO userDto) throws JsonParseException, Exception {
//		
//		Long epoch=System.currentTimeMillis();
//	
//		if(usersRepository.findAll(Example.of(new Users(
//			null, //userId
//			userDto.getUserName(), //userName
//			null, //userEmail
//			null, //userPhoneNumber
//			null, //userMotto
//			null, //userLinkedInUrl
//			null, //userBirthdate
//			null, //userBirthplace
//			null, //userGender
//			null, //userAddress
//			null, //userPostalCode
//			null, //userFeedbackMethod
//			null, //userLifestyleAspiration
//			null, //userRedflags
//			null, //userPredictableTimeOff
//			null, //userImageProfile
//			null, //userMaritalStatus
//			null, //isActive
//			null, //isFreshUser
//			null //userRealName
//		))).size()!=0){
//			return ResponseEntity.ok().body(new Response("99", "User dengan username yang sama sudah ada di dalam database!"));
//		}
//
//		Users user = new Users();
//		user.setUserName(userDto.getUserName());
//		user.setUserRealName(userDto.getUserRealName());
//		user.setUserPhoneNumber(userDto.getUserPhoneNumber());
//		user.setUserGender(userDto.getUserGender());
//		user.setUserEmail(userDto.getUserEmail());
//		user.setPassword(SHA_256.digestAsHex(userDto.getPassword()));
//		user.setCreatedBy(userCode);
//		user.setCreatedDate(new Date(epoch));
//		user.setUpdatedBy(userCode);
//		user.setUpdatedDate(new Date(epoch));
//		user.setIsActive((short)1);
//		Users response = usersRepository.save(user);

//		UserBranch userBranch = new UserBranch();
//		userBranch.setUser(response);
//		userBranch.setCreatedBy(userCode);
//		userBranch.setCreatedDate(new Date(epoch));
//		userBranch.setUpdatedBy(userCode);
//		userBranch.setUpdatedDate(new Date(epoch));
//		Optional<Branch> branch = branchRepository.findById(userDto.getBranchId());
//		userBranch.setBranch(branch.get());
//		userBranchRepository.save(userBranch);
//
//		UserRole userRole = new UserRole();
//		userRole.setUsers(response);
//		Optional<Role> role = roleRepository.findById(userDto.getRoleId());
//		userRole.setRole(role.get());
//		userRoleRepository.save(userRole);
		
//		return ResponseEntity.ok().body(new Response("00", "User berhasil ditambahkan"));
//	}

//	@Transactional
//	@PostMapping("/multiple_create_user_management")
//	public Object multipleCreate (@RequestHeader(value="user-code") String userCode,
//			@RequestParam("file") MultipartFile file,
//			RedirectAttributes redirectAttributes) throws Exception {
//
//		String fileName = file.getOriginalFilename();
//		storageService.store(file);
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//				.path("/downloadFile/")
//				.path(fileName)
//				.toUriString();
//		
//		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
//		XSSFSheet worksheet = workbook.getSheetAt(0);
//		
//		Long epoch = System.currentTimeMillis();
//		Map<String, Object> result = new HashMap<String,Object>();
//		List<Map<String,Object>> unquilifiedData = new ArrayList<Map<String,Object>>();
//		int successItems = 0;
//		
//		for(int i=1;i<worksheet.getPhysicalNumberOfRows();i++) {
//
//			XSSFRow row = worksheet.getRow(i);
//			StringBuilder message = new StringBuilder();
//			Map<String, Object> item = new HashMap<String, Object>();
//			int st = 0;
//
//			String userName = row.getCell(0) == null ? null : row.getCell(0).getStringCellValue();
//			
//			if (userName == null) {
//				continue;
//			} else if (userName.equals("")) {
//				continue;
//			} else {
//				
//				if(usersRepository.findAll(Example.of(new Users(
//					null, //userId
//					userName, //userName
//					null, //userEmail
//					null, //userPhoneNumber
//					null, //userMotto
//					null, //userLinkedInUrl
//					null, //userBirthdate
//					null, //userBirthplace
//					null, //userGender
//					null, //userAddress
//					null, //userPostalCode
//					null, //userFeedbackMethod
//					null, //userLifestyleAspiration
//					null, //userRedflags
//					null, //userPredictableTimeOff
//					null, //userImageProfile
//					null, //userMaritalStatus
//					null, //isActive
//					null, //isFreshUser
//					null //curRow.getCell(1).getStringCellValue(), //userRealName
//				))).size()==0){
//					
////					Branch branch = branchRepository.findBranchByBranchCode((row.getCell(5).getStringCellValue()));
////					if (branch == null) {
////						st = 1;
////						message.append("Kode Cabang tidak ditemukan atau salah <br/>");
////					}
////					Optional<Role> role = roleRepository.findById((long)row.getCell(6).getNumericCellValue());
////					if (role.isPresent() == false) {
////						st = 1;
////						message.append("Kode Role tidak ditemukan atau salah <br/>");
////					}
//					
//					if (st == 0) {
//						
//						Users user = new Users();
//						user.setUserName(row.getCell(0).getStringCellValue());
//						user.setUserRealName(row.getCell(1).getStringCellValue());
//						user.setUserEmail(row.getCell(2).getStringCellValue());
//						user.setUserPhoneNumber(row.getCell(3).getStringCellValue());
//						user.setUserGender(((Double)row.getCell(4).getNumericCellValue()).shortValue());
//						user.setPassword(SHA_256.digestAsHex(Constants.INITIAL_PASSWORD));
//						user.setCreatedBy(userCode);
//						user.setCreatedDate(new Date(epoch));
//						user.setUpdatedBy(userCode);
//						user.setUpdatedDate(new Date(epoch));
//						user.setIsActive((short)1);
//						Users response = usersRepository.save(user);
//
////						UserBranch userBranch = new UserBranch();
////						userBranch.setUser(response);
////						userBranch.setCreatedBy(userCode);
////						userBranch.setCreatedDate(new Date(epoch));
////						userBranch.setUpdatedBy(userCode);
////						userBranch.setUpdatedDate(new Date(epoch));
////						userBranch.setBranch(branch);
////						userBranchRepository.save(userBranch);
////
////						UserRole userRole = new UserRole();
////						userRole.setUsers(response);
////						userRole.setRole(role.get());
////						userRoleRepository.save(userRole);
//						
//						successItems++;
//						
//					} else {
//						
//						item.put("userName", userName);
//						item.put("message", message);
//						unquilifiedData.add(item);
//						
//					}
//					
//				} else {
//					
//					message.append("Username sudah dipakai");
//					item.put("userName", userName);
//					item.put("message", message);
//					unquilifiedData.add(item);
//					
//				}
//			}
//		}
//
//		result.put("successItems", successItems);
//		result.put("processFailed", unquilifiedData);
//		result.put("uploadFile", new UploadFileResponse(fileName, fileDownloadUri,file.getContentType(), file.getSize()));
//		workbook.close();
//		return result;
//	}
//
//	@Transactional
//	@PutMapping("/update_user_management")
//	public Object edit(@RequestHeader("user-code") String userCode, 
//		@RequestParam("userId") Long id, 
//		@RequestBody UpdateUserDTO userDto){
//		
//		Users oldUser = usersRepository.findById(id).orElse(null);
//		Long epoch=System.currentTimeMillis();
//
//		if(oldUser != null){
//			
//			oldUser.setUserName(userDto.getUserName());
//			oldUser.setUserRealName(userDto.getUserRealName());
//			oldUser.setUserPhoneNumber(userDto.getUserPhoneNumber());
//			oldUser.setUserGender(userDto.getUserGender());
//			oldUser.setUserEmail(userDto.getUserEmail());
//			oldUser.setUpdatedBy(userCode);
//			oldUser.setUpdatedDate(new Date(epoch));
//			usersRepository.save(oldUser);
//			
////			Optional<UserBranch> userBranch = userBranchRepository.findById(userDto.getUserBranchId());
////			userBranch.get().setUpdatedBy(userCode);
////			userBranch.get().setUpdatedDate(new Date(epoch));
////			Optional<Branch> branch = branchRepository.findById(userDto.getBranchId());
////			userBranch.get().setBranch(branch.get());
////			userBranchRepository.save(userBranch.get());
////
////			Optional<UserRole> userRole = userRoleRepository.findById(userDto.getUserRoleId());
////			Optional<Role> role = roleRepository.findById(userDto.getRoleId());
////			userRole.get().setRole(role.get());
////			userRoleRepository.save(userRole.get());
//
//			return ResponseEntity.ok().body(new Response("00", "User berhasil diupdate"));
//			
//		}else{
//			
//			return ResponseEntity.ok().body(new Response("99", "User tidak ditemukan"));
//			
//		}
//	}
	
	@Transactional
	@PutMapping("/change_password")
	public Object changePassword(@RequestParam("userId") Long id, @RequestBody ChangePasswordDTO userDto){
		
		Optional<Users> users = usersRepository.findById(id);
		
		if (users.get().getPassword().equals(HashUtil.SHA_256.digestAsHex(userDto.getOldPassword()))){
			
			users.get().setPassword(HashUtil.SHA_256.digestAsHex(userDto.getNewPassword()));
			usersRepository.save(users.get());
			
			return ResponseEntity.ok().body(new Response("00", "Password berhasil diupdate"));
			
		} else {
			return ResponseEntity.ok().body(new Response("99", "Password lama salah"));
		}
		
	}

}

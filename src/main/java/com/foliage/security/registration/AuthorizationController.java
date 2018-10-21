package com.foliage.security.registration;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foliage.api.common.ApiDBConstants;
import com.foliage.api.db.User;
import com.foliage.api.db.UserRepository;
import com.foliage.security.common.Info;
import com.foliage.security.common.InfoConstants;
import com.foliage.security.common.SecurityMapConstants;
import com.foliage.security.db.UserAuthData;
import com.foliage.security.db.UserSecRepository;


@Controller
@RequestMapping(path=SecurityMapConstants.SECURITY_URL)
public class AuthorizationController {
	
	@Autowired
	private UserSecRepository userSecRepo;
	
	@Autowired 
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	static Logger sLogger = org.slf4j.LoggerFactory.getLogger(AuthorizationController.class);
	//TODO
	//implement existsByMail()
	private boolean mailExists(String email) {
		return userSecRepo.findByEmail(email) != null;
	}
	
	//TODO!!!
	private String generateName(List<UserAuthData> names, String lastname, String firstname) {
		String sb = new String(lastname);
		int suffix = 1;
		List<String> str_names = names.stream().
										map(e->e.getName()).
										collect(Collectors.toList());

		if(!str_names.contains(lastname)) {
			return lastname;
		}
		
		while(str_names.contains(sb + suffix)) {
			suffix ++;
		}
		
		return sb + suffix;
	}
	
	@RequestMapping(path="/reg", method = RequestMethod.GET)
	public 	@ResponseBody Info authorize(
			@RequestParam(name = SecurityMapConstants.SECURITY_PATH_EMAIL, 		required = true) String email,
			@RequestParam(name = SecurityMapConstants.SECURITY_PATH_PASSWORD, 	required = true) String pwd,
			@RequestParam(name = SecurityMapConstants.SECURITY_PATH_NAME, required = false, defaultValue = "") String name,
			@RequestParam(name = ApiDBConstants.USER_FIRSTNAME_COLUMN, 			required = true) String firstname,
			@RequestParam(name = ApiDBConstants.USER_LASTNAME_COLUMN, 			required = true) String lastname) {
		
		if(name.isEmpty()) {/*if name was not given we have to generate it depending on lastname*/
			List<UserAuthData> users = userSecRepo.findNamesByName(lastname);
			name = users.isEmpty() ? lastname : generateName(users, lastname, firstname);
		}
		
		if(mailExists(email)) {
			Info inf = new Info(false, InfoConstants.EMAIL_ALREADY_EXISTS);
			sLogger.info(inf.toString());
			return inf;
		}
		
		User user_main = new User();
		userRepo.save(user_main.
				setFirstname(firstname).
				setLastname(lastname));

		UserAuthData user_auth = new UserAuthData();
		userSecRepo.save(user_auth.
				setEmail(email).
				setPassword(passwordEncoder.encode(pwd)).
				setName(name));
		
		return new Info(true);
	}
}

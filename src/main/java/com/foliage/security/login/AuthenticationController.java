package com.foliage.security.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foliage.security.common.Info;
import com.foliage.security.common.InfoConstants;
import com.foliage.security.common.SecurityMapConstants;
import com.foliage.security.common.SecurityUtils;
import com.foliage.security.db.UserAuthData;
import com.foliage.security.db.UserSecRepository;

@Component
@Controller
@RequestMapping(path=SecurityMapConstants.SECURITY_URL)
public class AuthenticationController {

	@Autowired
	private UserSecRepository userRepo;
		
	private static Info info;
	
	/**
	 * @author Mischa Rodchenkov
	 * @param value {@link String} <b>is users email or his unique name</b>
	 * @param password {@link String}
	 * @return {@link Info} in JSON
	 */
	@RequestMapping(path=SecurityMapConstants.LOGIN_URL)
	public @ResponseBody Info isAuthenticated(@RequestParam(name=SecurityMapConstants.SECURITY_PATH_NAME) String value, @RequestParam(name=SecurityMapConstants.SECURITY_PATH_PASSWORD) String password) {
		UserAuthData user = SecurityUtils.isEmail(value) ? userRepo.findByEmail(value) : userRepo.findByName(value);
		boolean isPwdCorrect;
		info = new Info();
		if(null == user || null == user.getPassword()) {
			info.setStatus(false);
			info.setDescription(InfoConstants.NO_USER_WITH_EMAIL_FOUND);
			return info;
		}
		isPwdCorrect = BCrypt.checkpw(password, user.getPassword());
		if(isPwdCorrect) {
			info.setStatus(true);
			info.setDescription(user);
		}else {
			info.setStatus(false);
			info.setDescription(InfoConstants.WRONG_PASSWORD_OR_EMAIL);
		}
		return info;
	}
}

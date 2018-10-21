package com.foliage.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foliage.api.common.ApiMapConstants;
import com.foliage.api.common.Info;
import com.foliage.api.db.User;
import com.foliage.api.db.UserRepository;

@Controller
@RequestMapping(path=ApiMapConstants.USER_URL)
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(path=ApiMapConstants.GET_USER_PATH + "/{id}")
	public @ResponseBody Info<User> getUser(@PathVariable int id) {
		User user = userRepository.findById(id).get();
		
		Info<User> inf = new Info<User>();
		inf.setStatus(true);
		inf.setDescription("test successful");
		inf.setSubject(user);
		return inf;
	}
}

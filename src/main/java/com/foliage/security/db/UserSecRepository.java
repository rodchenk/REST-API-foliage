package com.foliage.security.db;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UserSecRepository  extends CrudRepository<UserAuthData, Integer>{
	
	UserAuthData findByEmail(String email);
	
	UserAuthData findByName(String name);
	
	List<UserAuthData> findNamesByName(String name);
}

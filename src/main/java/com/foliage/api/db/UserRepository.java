package com.foliage.api.db;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}

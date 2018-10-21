package com.foliage.security.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.foliage.security.common.DBConstants;

public class ExtendedUserRepository extends SimpleJpaRepository<UserAuthData, Integer> implements UserSecRepository{
	
	private EntityManager entityManager;
	
	public ExtendedUserRepository(JpaEntityInformation<UserAuthData, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
        this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public UserAuthData findByEmail(String email) {
		return this.findByAttributeName(DBConstants.USER_EMAIL_COLUMN_NAME, email);
	}

	@Override
	@Transactional
	public UserAuthData findByName(String name) {
		return this.findByAttributeName(DBConstants.USER_AUTH_COLUMN_NAME, name);
	}
	
	/**
	 * @author Mischa Rodchenkov
	 * @param attr {@link DBConstants} <b>Column name from DB Table</b>
	 * @param value {@link String} <b>Value for 'WHERE' clause</b>
	 * @return {@link UserAuthData} by given query
	 */
	public UserAuthData findByAttributeName(String attr, String value) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<UserAuthData> cQuery = builder.createQuery(getDomainClass());
	    Root<UserAuthData> root = cQuery.from(getDomainClass());
	    cQuery
	      .select(root)
	      .where(builder.equal(root.<String>get(attr), value));
	    TypedQuery<UserAuthData> query = entityManager.createQuery(cQuery);
	    return query.getSingleResult();
	}

	@Override
	@Transactional
	public List<UserAuthData> findNamesByName(String name) {
				
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<UserAuthData> cQuery = builder.createQuery(UserAuthData.class);
	    Root<UserAuthData> root = cQuery.from(UserAuthData.class);
		
	    cQuery
	      .select(root.get(DBConstants.USER_AUTH_COLUMN_NAME))
	      	.where(builder.
	      		like(root.get(DBConstants.USER_AUTH_COLUMN_NAME), name + "%"));
	    TypedQuery<UserAuthData> query = entityManager.createQuery(cQuery);
	    
	    return query.getResultList();
	}
}

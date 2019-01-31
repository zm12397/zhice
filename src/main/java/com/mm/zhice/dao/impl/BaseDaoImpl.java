package com.mm.zhice.dao.impl;

import com.mm.zhice.dao.BaseDao;
import com.mm.zhice.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.Field;


public class BaseDaoImpl<T, ID extends Serializable>extends SimpleJpaRepository<T, ID>
	implements BaseDao<T, ID> {
	private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
	/**
	 * 持久化上下文
	 */
	private final EntityManager entityManager;

	public BaseDaoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {

		super(entityInformation, em);

		this.entityManager = em;

	}

	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
		// TODO Auto-generated constructor stub
	}


	@Override
	public void save(Object entity, boolean ignoreNullProperties, boolean ignoreCollections,
			String... ignoreProperties) throws IllegalArgumentException, IllegalAccessException, IntrospectionException {
		// TODO Auto-generated method stub
		Class clazz = entity.getClass();
		Object source = null;
		for(Field field : clazz.getDeclaredFields()){
			if(field.getAnnotation(Id.class) != null){
				field.setAccessible(true);
				Object id = field.get(entity);
				if(id != null){
					source = entityManager.find(clazz, id);
					if(source != null){
						EntityUtils.copyProperties(entity,source,ignoreNullProperties,ignoreCollections,ignoreProperties);
					}
				}
				break;
			}
		}
		entityManager.merge(source);
		entityManager.flush();
	}
}

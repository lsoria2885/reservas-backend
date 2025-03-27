package com.mitocode.service.impl;

import java.lang.reflect.Method;
import java.util.List;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.ICRUD;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract IGenericRepo<T, ID> getRepo();

	@Override
	public T save(T t) throws Exception {
		return getRepo().save(t);
	}

	@Override
	public T update(ID id, T t) throws Exception {
		Class<?> clazz = t.getClass();
		String className = clazz.getSimpleName();
		String methodName = "setId" + className;
		Method setIdMethod = clazz.getMethod(methodName, id.getClass());
		setIdMethod.invoke(t, id);

		getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
		return getRepo().save(t);
	}

	@Override
	public List<T> findAll() throws Exception {
		return getRepo().findAll();
	}

	@Override
	public T findById(ID id) throws Exception {
		return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
	}

	@Override
	public void delete(ID id) throws Exception {
		getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
		getRepo().deleteById(id);
	}
}

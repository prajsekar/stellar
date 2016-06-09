package org.citrya.stellar.data.service;

import java.util.List;

public interface DataService <T> {
	T create(T object);
	T delete(Long id);
	T update(T object);
	List<T> getAll();
	T get(Long id);
}

package org.citrya.stellar.data.service;

import java.util.List;

public interface DataService <T> {
	T create(T object);
	T delete(int id);
	T update(T object);
	List<T> getAll();
	T get(int id);
}

package com.rodrigopeleias.mywinecollection.service;

import com.rodrigopeleias.mywinecollection.model.User;

public interface UserService {

	User save(User user);

	User findOne(Long id);

	void delete(Long id);

}
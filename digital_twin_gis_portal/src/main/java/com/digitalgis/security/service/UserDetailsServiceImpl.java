package com.digitalgis.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalgis.dao.UserDao;
import com.digitalgis.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userdao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = null;
		try {
			System.out.println("username " + userName);
			user = userdao.checkUserLoginDeatils(userName);
			if (user == null || user.getUserId() == null) {
				throw new UsernameNotFoundException("User is either inactive, deleted or not available.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UserPrinciple.build(user);
	}

}

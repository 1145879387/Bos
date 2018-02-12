package com.bos.dao.base;

import com.bos.domain.User;

public interface IuserDao extends Ibasedao<User> {

	User finduserbyusernameandpassword(String username, String generate);

	User findUserByUsername(String username);
}

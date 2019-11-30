package org.video.admin.service;


import org.video.admin.common.utils.PagedResult;
import org.video.admin.pojo.Users;

public interface UsersService {

	public PagedResult queryUsers(Users user, Integer page, Integer pageSize);
	
}

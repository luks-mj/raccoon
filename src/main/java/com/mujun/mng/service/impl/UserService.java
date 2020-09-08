package com.mujun.mng.service.impl;

import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.dao.UserDao;
import com.mujun.mng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Map<String, Object>> queryUserInfo() throws BaseException {
        return userDao.queryUserInfo();
    }

}

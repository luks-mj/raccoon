package com.mujun.mng.service;

import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    List<Map<String, Object>> queryUserInfo () throws BaseException;

    Integer insert(User user) throws BaseException;
}

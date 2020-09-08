package com.mujun.mng.service;

import com.mujun.mng.commons.exception.BaseException;

import java.util.List;
import java.util.Map;

public interface IUserService {

    List<Map<String, Object>> queryUserInfo () throws BaseException;
}

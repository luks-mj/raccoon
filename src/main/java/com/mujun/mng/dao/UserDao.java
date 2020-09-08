package com.mujun.mng.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    public List<Map<String, Object>> queryUserInfo();

}

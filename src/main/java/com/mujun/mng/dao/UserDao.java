package com.mujun.mng.dao;

import com.mujun.mng.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao extends BaseMapper<User> {

    public List<Map<String, Object>> queryUserInfo();

}

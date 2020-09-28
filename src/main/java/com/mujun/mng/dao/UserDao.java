package com.mujun.mng.dao;

import com.mujun.mng.model.Role;
import com.mujun.mng.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loadUserByUsername(String username);

    List<Role> getUserRolesById(Integer id);

    List<User> getAllUsers(@Param("urid") Integer hrid, @Param("keywords") String keywords);

    List<User> getAllUsersExceptCurrentUser(Integer id);

    Integer updatePasswd(@Param("urid") Integer hrid, @Param("encodePass") String encodePass);

    Integer updateUserface(@Param("url") String url, @Param("id") Integer id);
}

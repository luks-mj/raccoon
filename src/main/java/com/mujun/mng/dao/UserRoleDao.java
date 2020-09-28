package com.mujun.mng.dao;

import com.mujun.mng.model.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleDao {

        int deleteByPrimaryKey(Integer id);

        int insert(UserRole record);

        int insertSelective(UserRole record);

        UserRole selectByPrimaryKey(Integer id);

        int updateByPrimaryKeySelective(UserRole record);

        int updateByPrimaryKey(UserRole record);

        void deleteByUrid(Integer hrid);

        Integer addRole(@Param("hrid") Integer hrid, @Param("rids") Integer[] rids);
}

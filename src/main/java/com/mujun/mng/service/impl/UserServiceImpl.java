package com.mujun.mng.service.impl;

import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.commons.utils.UserUtils;
import com.mujun.mng.dao.UserDao;
import com.mujun.mng.dao.UserRoleDao;
import com.mujun.mng.model.User;
import com.mujun.mng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 用户菜单权限配置，及单台登录
 */

@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService , UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public List<Map<String, Object>> queryUserInfo() throws BaseException {
        return null;
    }

    @Override
    public Integer insert(User user) throws BaseException {
        return userDao.insert(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =userDao.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        user.setRoles(userDao.getUserRolesById(user.getId()));
        return user;
    }

    public List<User> getAllUsers(String keywords) {
        return userDao.getAllUsers(UserUtils.getCurrentHr().getId(),keywords);
    }

    @Transactional
    public boolean updateHrRole(Integer urid, Integer[] rids) {
        userRoleDao.deleteByUrid(urid);
        return userRoleDao.addRole(urid, rids) == rids.length;
    }


    public Integer deleteUserById(Integer id) {
        return userRoleDao.deleteByPrimaryKey(id);
    }

    public List<User> getAllHrsExceptCurrentHr() {
        return userDao.getAllUsersExceptCurrentUser(UserUtils.getCurrentHr().getId());
    }

    public Integer updateHyById(User hr) {
        return userDao.updateByPrimaryKeySelective(hr);
    }

    public boolean updateHrPasswd(String oldpass, String pass, Integer urid) {
        User hr = userDao.selectByPrimaryKey(urid);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldpass, hr.getPassword())) {
            String encodePass = encoder.encode(pass);
            Integer result = userDao.updatePasswd(urid, encodePass);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }

    public Integer updateUserface(String url, Integer id) {
        return userDao.updateUserface(url, id);
    }

}

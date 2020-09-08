package com.mujun.mng.commons.smo.impl;


import org.springframework.stereotype.Service;

@Service("SnifferDBSMOImpl")
public class SnifferDBSMOImpl {

    public String addDBTest() {
        // 起事务，就会初始化数据源。检测数据库访问是否正常
        return "success";
    }

}

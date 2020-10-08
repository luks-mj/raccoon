package com.mujun.mng.service.mapperservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mujun.mng.dao.EnterpriseDao;
import com.mujun.mng.model.EnterpriseModel;
import com.mujun.mng.service.mapperservice.IQueryEnterpriseService;
import org.springframework.stereotype.Service;

@Service
public class QueryEnterpriseServiceImpl extends ServiceImpl<EnterpriseDao, EnterpriseModel> implements IQueryEnterpriseService {

}

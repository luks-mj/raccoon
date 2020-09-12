package com.mujun.mng.service.mapperservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mujun.mng.dao.EnterpriseCountIptDao;
import com.mujun.mng.model.EnterpriseCountModel;
import com.mujun.mng.service.mapperservice.IQueryEnterpriseCountService;
import org.springframework.stereotype.Service;

@Service
public class QueryEnterpriseCountServiceImpl extends ServiceImpl<EnterpriseCountIptDao, EnterpriseCountModel> implements IQueryEnterpriseCountService {

}

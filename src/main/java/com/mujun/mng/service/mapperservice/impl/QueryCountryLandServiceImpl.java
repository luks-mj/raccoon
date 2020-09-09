package com.mujun.mng.service.mapperservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mujun.mng.dao.CountryLandDao;
import com.mujun.mng.model.CountryLandModel;
import com.mujun.mng.service.mapperservice.IQueryCountryLandService;
import org.springframework.stereotype.Service;

@Service
public class QueryCountryLandServiceImpl extends ServiceImpl<CountryLandDao, CountryLandModel> implements IQueryCountryLandService {

}

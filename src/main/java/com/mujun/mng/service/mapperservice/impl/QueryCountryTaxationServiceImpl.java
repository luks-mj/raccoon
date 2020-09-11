package com.mujun.mng.service.mapperservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mujun.mng.dao.CountryTaxationDao;
import com.mujun.mng.model.CountryTaxationModel;
import com.mujun.mng.service.mapperservice.IQueryCountryTaxationService;
import org.springframework.stereotype.Service;

@Service
public class QueryCountryTaxationServiceImpl extends ServiceImpl<CountryTaxationDao, CountryTaxationModel>  implements IQueryCountryTaxationService {

}

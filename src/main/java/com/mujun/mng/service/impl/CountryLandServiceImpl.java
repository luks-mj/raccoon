package com.mujun.mng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.dao.ContryLandDao;
import com.mujun.mng.model.ContryLandModel;
import com.mujun.mng.service.ICountryLandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("CountryLandServiceImpl")
public class CountryLandServiceImpl implements ICountryLandService {

    @Autowired
    private ContryLandDao contryLandDao;
    @Override
    public List<ContryLandModel> queryCountryLandData() throws BaseException {
        QueryWrapper<ContryLandModel> wrapper = new QueryWrapper<ContryLandModel>();
        return contryLandDao.selectList(wrapper);
    }
}

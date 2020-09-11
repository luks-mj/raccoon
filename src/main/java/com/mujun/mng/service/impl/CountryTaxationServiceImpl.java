package com.mujun.mng.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.model.CountryTaxationModel;
import com.mujun.mng.service.ICountryTaxationService;
import com.mujun.mng.service.mapperservice.impl.QueryCountryTaxationServiceImpl;
import com.mujun.mng.vo.EnterpriseModeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("CountryTaxationServiceImpl")
public class CountryTaxationServiceImpl implements ICountryTaxationService {


    @Autowired
    private QueryCountryTaxationServiceImpl queryCountryTaxationService;

    @Override
    public Map<String, Object> queryCountryTaxation(EnterpriseModeVo enterpriseModeVo) throws BaseException {
        return null;
    }

    @Override
    public void batchImport(List<String[]> list) throws BaseException {
        List<CountryTaxationModel> entityList = new ArrayList<>(list.size());
        for (String[] str : list) {
            CountryTaxationModel countryTaxationModel = new CountryTaxationModel();
            if (str.length > 4) {
                countryTaxationModel.setId(IdWorker.getIdStr(countryTaxationModel));
                countryTaxationModel.setEnterPriseName(StringUtils.isEmpty(str[0]) ? " " : str[0]);
                countryTaxationModel.setEnterPriseCode(StringUtils.isEmpty(str[1]) ? " " : str[1]);
                countryTaxationModel.setTaxationData(StringUtils.isEmpty(str[2]) ? 0 : Long.valueOf(str[2]));
                countryTaxationModel.setTaxationDetail(StringUtils.isEmpty(str[3]) ? " " : str[3]);
                countryTaxationModel.setUserId(null);
                countryTaxationModel.setCreateDate(new Date());
                countryTaxationModel.setUpdateDate(new Date());
            }
            entityList.add(countryTaxationModel);
        }
        if (entityList.size()==1000){
            queryCountryTaxationService.saveBatch(entityList);
            entityList.clear();

        }
        queryCountryTaxationService.saveBatch(entityList);

    }

    @Override
    public void deleteCountryTaxationData(EnterpriseModeVo enterpriseModeVo) throws BaseException {

    }
}

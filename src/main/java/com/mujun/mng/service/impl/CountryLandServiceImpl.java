package com.mujun.mng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mujun.mng.commons.config.CustomIdGenerator;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.dao.CountryLandDao;
import com.mujun.mng.model.CountryLandModel;
import com.mujun.mng.service.ICountryLandService;
import com.mujun.mng.service.mapperservice.impl.QueryCountryLandServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("CountryLandServiceImpl")
public class CountryLandServiceImpl implements ICountryLandService {

    @Autowired
    private CountryLandDao countryLandDao;

    @Autowired
    private QueryCountryLandServiceImpl queryCountryLandService;

    @Autowired
    private CustomIdGenerator customIdGenerator;

    @Override
    public Map<String, Object> queryCountryLandData() throws BaseException {

        int pageNo = 1;
        int pageSize = 0;
        QueryWrapper<CountryLandModel> wrapper = new QueryWrapper<CountryLandModel>();
        Map<String, Object> resultMap = new HashMap<> ();

        Page page = PageHelper.startPage(pageNo, pageSize,true);
        List<CountryLandModel> list =  countryLandDao.selectList(wrapper);
        Map<String,Object> pager = new HashMap<>();
        pager.put("total",page.getTotal());
        pager.put("startRowNum",page.getStartRow());
        pager.put("endRowNum",page.getEndRow());
        pager.put("pageSize",page.getPageSize());
        pager.put("totalPage",page.getPages());
        resultMap.put("pager", pager);
        resultMap.put("resultList", list);
        return resultMap;
    }

    @Override
    public void batchImport(List<String[]> list) throws BaseException {
        List<CountryLandModel> entityList = new ArrayList<>(list.size());
            CountryLandModel countryLandModel = new CountryLandModel();
            for (String[] str : list) {
                if (str.length > 5) {
                    countryLandModel.setId(customIdGenerator.nextId(CountryLandModel.class));
                    countryLandModel.setEnterPriseName(StringUtils.isEmpty(str[0]) ? " " : str[0]);
                    countryLandModel.setEnterPriseCode(StringUtils.isEmpty(str[1]) ? " " : str[1]);
                    countryLandModel.setRegisterArea(StringUtils.isEmpty(str[2]) ? 0 : Long.valueOf(str[2]));
                    countryLandModel.setLesseeArea(StringUtils.isEmpty(str[3]) ? 0 : Long.valueOf(str[3]));
                    countryLandModel.setLeaseArea(StringUtils.isEmpty(str[4]) ? 0 : Long.valueOf(str[4]));
                    countryLandModel.setUserId(null);
                    countryLandModel.setCreateDate(new Date());
                    countryLandModel.setUpdateDate(new Date());
                }
                entityList.add(countryLandModel);
            }
        if (entityList.size()==1000){
            queryCountryLandService.saveBatch(entityList);
            entityList.clear();

        }
        queryCountryLandService.saveBatch(entityList);
    }
}
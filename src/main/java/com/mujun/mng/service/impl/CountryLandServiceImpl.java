package com.mujun.mng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.dao.CountryLandDao;
import com.mujun.mng.model.CountryLandModel;
import com.mujun.mng.service.ICountryLandService;
import com.mujun.mng.service.mapperservice.impl.QueryCountryLandServiceImpl;
import com.mujun.mng.vo.CountryLandModeVo;
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


    @Override
    public Map<String, Object> queryCountryLandData(CountryLandModeVo countryLandModeVo) throws BaseException {

        int pageNo = 15;
        int pageSize = 0;
        QueryWrapper<CountryLandModel> wrapper = new QueryWrapper<CountryLandModel>();
        Map<String, Object> resultMap = new HashMap<> ();
        if (!org.springframework.util.StringUtils.isEmpty(countryLandModeVo.getPageNo())){
            pageNo = countryLandModeVo.getPageNo();
        }
        if (!org.springframework.util.StringUtils.isEmpty(countryLandModeVo.getPageSize())){
            pageSize = countryLandModeVo.getPageSize();
        }
        if (!org.springframework.util.StringUtils.isEmpty(countryLandModeVo.getEnterpriseCode())){
                wrapper.eq("enterprise_code",countryLandModeVo.getEnterpriseCode());
        }
        if (!org.springframework.util.StringUtils.isEmpty(countryLandModeVo.getEnterpriseName())){
            wrapper.eq("enterprise_name",countryLandModeVo.getEnterpriseName());
        }
        if (!org.springframework.util.StringUtils.isEmpty(countryLandModeVo.getCreateDate())){
            wrapper.like("create_date",countryLandModeVo.getCreateDate());
        }
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
            for (String[] str : list) {
                CountryLandModel countryLandModel = new CountryLandModel();
                if (str.length > 5) {
                    countryLandModel.setId(IdWorker.getId(countryLandModel));
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

    @Override
    public void deleteCountryLandData(CountryLandModeVo countryLandModeVo) throws BaseException {
        QueryWrapper<CountryLandModel> wrapper = new QueryWrapper<CountryLandModel>();
         if (org.springframework.util.StringUtils.isEmpty(countryLandModeVo.getEnterpriseCode()) &&
                 org.springframework.util.StringUtils.isEmpty(countryLandModeVo.getEnterpriseName()) )
         {
             throw new BaseException("请至少选择一项企业名称，或者企业统一认证码！");
         }
        if (!org.springframework.util.StringUtils.isEmpty(countryLandModeVo.getEnterpriseCode())){
            wrapper.eq("enterprise_code",countryLandModeVo.getEnterpriseCode());
        }
        if (!org.springframework.util.StringUtils.isEmpty(countryLandModeVo.getEnterpriseName())){
            wrapper.eq("enterprise_name",countryLandModeVo.getEnterpriseName());
        }
        queryCountryLandService.remove(wrapper);
    }

}

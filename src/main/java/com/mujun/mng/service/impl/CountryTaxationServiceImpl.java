package com.mujun.mng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.dao.CountryTaxationDao;
import com.mujun.mng.model.CountryLandModel;
import com.mujun.mng.model.CountryTaxationModel;
import com.mujun.mng.service.ICountryTaxationService;
import com.mujun.mng.service.mapperservice.impl.QueryCountryTaxationServiceImpl;
import com.mujun.mng.vo.EnterpriseModeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("CountryTaxationServiceImpl")
public class CountryTaxationServiceImpl implements ICountryTaxationService {


    @Autowired
    private QueryCountryTaxationServiceImpl queryCountryTaxationService;

    @Autowired
    private CountryTaxationDao countryTaxationDao;

    @Override
    public Map<String, Object> queryCountryTaxation(EnterpriseModeVo enterpriseModeVo) throws BaseException {

        int pageNo = 15;
        int pageSize = 0;
        QueryWrapper<CountryTaxationModel> wrapper = new QueryWrapper<CountryTaxationModel>();
        Map<String, Object> resultMap = new HashMap<>();
        if (!org.springframework.util.StringUtils.isEmpty(enterpriseModeVo.getPageNo())){
            pageNo = enterpriseModeVo.getPageNo();
        }
        if (!org.springframework.util.StringUtils.isEmpty(enterpriseModeVo.getPageSize())){
            pageSize = enterpriseModeVo.getPageSize();
        }
        if (!org.springframework.util.StringUtils.isEmpty(enterpriseModeVo.getEnterpriseCode())){
            wrapper.eq("enterprise_code",enterpriseModeVo.getEnterpriseCode());
        }
        if (!org.springframework.util.StringUtils.isEmpty(enterpriseModeVo.getEnterpriseName())){
            wrapper.like("enterprise_name",enterpriseModeVo.getEnterpriseName());
        }
        if (!org.springframework.util.StringUtils.isEmpty(enterpriseModeVo.getCreateDate())){
            wrapper.like("create_date",enterpriseModeVo.getCreateDate());
        }
        Page page = PageHelper.startPage(pageNo, pageSize,true);
        List<CountryTaxationModel> list =  countryTaxationDao.selectList(wrapper);
        Map<String,Object> pager = new HashMap<>();
//        pager.put("total",page.getTotal());
//        pager.put("startRowNum",page.getStartRow());
//        pager.put("endRowNum",page.getEndRow());
//        pager.put("pageSize",page.getPageSize());
//        pager.put("totalPage",page.getPages());
//        resultMap.put("pager", pager);
        resultMap.put("data", list);
        resultMap.put("total",page.getTotal());
        return resultMap;
    }

    @Override
    public void batchImport(List<String[]> list) throws BaseException {
        List<CountryTaxationModel> entityList = new ArrayList<>(list.size());
        for (String[] str : list) {
            CountryTaxationModel countryTaxationModel = new CountryTaxationModel();
            if (str.length == 4) {
                countryTaxationModel.setId(IdWorker.getIdStr(countryTaxationModel));
                countryTaxationModel.setEnterPriseName(StringUtils.isEmpty(str[0]) ? " " : str[0]);
                countryTaxationModel.setEnterPriseCode(StringUtils.isEmpty(str[1]) ? " " : str[1]);
                countryTaxationModel.setTaxationData(StringUtils.isEmpty(str[2]) ? 0 : Double.valueOf(str[2]));
                countryTaxationModel.setTaxationDetail(StringUtils.isEmpty(str[3]) ? " " : str[3]);
                countryTaxationModel.setUserId(null);
                countryTaxationModel.setCreateDate(new Date());
                countryTaxationModel.setUpdateDate(new Date());

                // 只导入不存在的企业，根据统一认证的企业编号判断库中是否已经存在，若已经存在 过滤。
                if (isExists(countryTaxationModel)){
                    continue;
                }
            }
            entityList.add(countryTaxationModel);
        }
        if (entityList.size()==1000){
            queryCountryTaxationService.saveBatch(entityList);
            entityList.clear();

        }
        queryCountryTaxationService.saveBatch(entityList);

    }



    /**
     * 判断当前库中是否存在统一企业认证
     * @param CountryTaxationModel
     * @return
     */
    public boolean isExists(CountryTaxationModel countryTaxationModel){
        QueryWrapper<CountryTaxationModel> wrapper = new QueryWrapper<CountryTaxationModel>();
        wrapper.eq("enterprise_code",countryTaxationModel.getEnterPriseCode());
        CountryTaxationModel res =  queryCountryTaxationService.getOne(wrapper);
        if (res!=null){
            return true;
        }
        return false;
    }


    @Override
    public void deleteCountryTaxationData(EnterpriseModeVo enterpriseModeVo) throws BaseException {
        QueryWrapper<CountryTaxationModel> wrapper = new QueryWrapper<CountryTaxationModel>();
        if (org.springframework.util.StringUtils.isEmpty(enterpriseModeVo.getEnterpriseCode()) &&
                org.springframework.util.StringUtils.isEmpty(enterpriseModeVo.getEnterpriseName()) )
        {
            throw new BaseException("请至少选择一项企业名称，或者企业统一认证码！");
        }
        if (!org.springframework.util.StringUtils.isEmpty(enterpriseModeVo.getEnterpriseCode())){
            wrapper.eq("enterprise_code",enterpriseModeVo.getEnterpriseCode());
        }
        if (!org.springframework.util.StringUtils.isEmpty(enterpriseModeVo.getEnterpriseName())){
            wrapper.eq("enterprise_name",enterpriseModeVo.getEnterpriseName());
        }
        queryCountryTaxationService.remove(wrapper);
    }
}

package com.mujun.mng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.model.EnterpriseCountModel;
import com.mujun.mng.service.IEnterpriseCountService;
import com.mujun.mng.service.mapperservice.impl.QueryEnterpriseCountServiceImpl;
import com.mujun.mng.vo.EnterpriseModeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("EnterpriseCountServiceImpl")
public class EnterpriseCountServiceImpl implements IEnterpriseCountService {


    @Autowired
    private QueryEnterpriseCountServiceImpl queryEnterpriseCountService;

    @Override
    public Map<String, Object> queryEnterprise(EnterpriseModeVo enterpriseModeVo) throws BaseException {
        return null;
    }

    @Override
    public void batchImport(List<String[]> list,EnterpriseModeVo enterpriseModeVo) throws BaseException {
        List<EnterpriseCountModel> entityList = new ArrayList<>(list.size());
        for (String[] str : list) {
            EnterpriseCountModel enterpriseCountModel = new EnterpriseCountModel();
            if (str.length == 7) {
                enterpriseCountModel.setId(IdWorker.getIdStr(enterpriseCountModel));
                enterpriseCountModel.setYear(enterpriseModeVo.getYear());
                enterpriseCountModel.setMonth(enterpriseCountModel.getMonth());
                enterpriseCountModel.setEnterPriseName(StringUtils.isEmpty(str[0]) ? " " : str[0]);
                enterpriseCountModel.setEnterPriseCode(StringUtils.isEmpty(str[1]) ? " " : str[1]);
                enterpriseCountModel.setIndustryPro(StringUtils.isEmpty(str[2]) ? 0 : Double.valueOf(str[2]));
                enterpriseCountModel.setIndustryPut(StringUtils.isEmpty(str[3]) ? 0 : Double.valueOf(str[3]));
                enterpriseCountModel.setIndustryRatio(StringUtils.isEmpty(str[4]) ? 0 : Double.valueOf(str[4]));
                enterpriseCountModel.setIndustryAvgPerson(StringUtils.isEmpty(str[5]) ? 0 : Integer.valueOf(str[5]));
                enterpriseCountModel.setEnergyConsumption(StringUtils.isEmpty(str[6]) ? 0 : Double.valueOf(str[6]));
                enterpriseCountModel.setUserId(null);
                enterpriseCountModel.setCreateDate(new Date());
                enterpriseCountModel.setUpdateDate(new Date());

                // 只导入不存在的企业，根据统一认证的企业编号判断库中是否已经存在，若已经存在 过滤。
                if (isExists(enterpriseCountModel)){
                    continue;
                }
            }
            entityList.add(enterpriseCountModel);
        }
        if (entityList.size()==1000){
            queryEnterpriseCountService.saveBatch(entityList);
            entityList.clear();

        }
        queryEnterpriseCountService.saveBatch(entityList);

    }

    @Override
    public void deleteEnterpriseCountData(EnterpriseModeVo enterpriseModeVo) throws BaseException {

        if (enterpriseModeVo.getIds().size()>0){
            queryEnterpriseCountService.removeByIds(enterpriseModeVo.getIds());
        }
    }

    /**
     * 判断当前库中是否存在统一企业认证
     * @param EnterpriseCountModel
     * @return
     */
    public boolean isExists(EnterpriseCountModel enterpriseCountModel){
        QueryWrapper<EnterpriseCountModel> wrapper = new QueryWrapper<EnterpriseCountModel>();
        wrapper.eq("enterprise_code",enterpriseCountModel.getEnterPriseCode());
        EnterpriseCountModel res =  queryEnterpriseCountService.getOne(wrapper);
        if (res!=null){
            return true;
        }
        return false;
    }
}

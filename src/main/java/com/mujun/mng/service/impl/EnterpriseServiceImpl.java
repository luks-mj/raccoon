package com.mujun.mng.service.impl;

import com.mujun.mng.service.mapperservice.impl.QueryEnterpriseServiceImpl;
import com.mujun.mng.dao.EnterpriseDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.model.EnterpriseModel;
import com.mujun.mng.service.IEnterpriseService;
import com.mujun.mng.vo.EnterpriseModeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("EnterpriseServiceImpl")
public class EnterpriseServiceImpl implements IEnterpriseService {

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private QueryEnterpriseServiceImpl queryEnterpriseService;


    @Override
    public Map<String, Object> queryEnterpriseData(EnterpriseModeVo enterpriseModeVo) throws BaseException {

        int pageNo = 15;
        int pageSize = 0;
        QueryWrapper<EnterpriseModel> wrapper = new QueryWrapper<EnterpriseModel>();
        Map<String, Object> resultMap = new HashMap<> ();
        if (enterpriseModeVo.getYear()==null){
            throw new BaseException("请选择年份");
        }

        if (enterpriseModeVo.getMonth()==null){
            throw new BaseException("请选择月份");
        }

        wrapper.eq("year",enterpriseModeVo.getYear());
        wrapper.eq("month",enterpriseModeVo.getMonth());

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
        List<EnterpriseModel> list =  enterpriseDao.selectList(wrapper);
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
    public void batchImport(List<String[]> list,EnterpriseModeVo enterpriseModeVo) throws BaseException {
        List<EnterpriseModel> entityList = new ArrayList<>(list.size());
            for (String[] str : list) {
                EnterpriseModel enterpriseModel = new EnterpriseModel();
                if (str.length == 9) {
                    enterpriseModel.setId(IdWorker.getIdStr(enterpriseModel));
                    enterpriseModel.setYear(enterpriseModeVo.getYear());
                    enterpriseModel.setMonth(enterpriseModeVo.getMonth());
                    enterpriseModel.setEnterPriseName(StringUtils.isEmpty(str[0]) ? " " : str[0]);
                    enterpriseModel.setEnterPriseCode(StringUtils.isEmpty(str[1]) ? " " : str[1]);
                    enterpriseModel.setIndustryType(StringUtils.isEmpty(str[2]) ? " " : str[2]);
                    enterpriseModel.setStatus(StringUtils.isEmpty(str[3]) ? " " : str[3]);
                    enterpriseModel.setEnterpriseType(StringUtils.isEmpty(str[4]) ? " " : str[4]);
                    enterpriseModel.setBelongNetwork(StringUtils.isEmpty(str[5]) ? " " : str[5]);
                    enterpriseModel.setDetailAddress(StringUtils.isEmpty(str[6]) ? " " : str[6]);
                    enterpriseModel.setContact(StringUtils.isEmpty(str[7]) ? " " : str[7]);
                    enterpriseModel.setContactNum(StringUtils.isEmpty(str[8]) ? " " : str[8]);
                    enterpriseModel.setUserId(null);
                    enterpriseModel.setCreateDate(new Date());
                    enterpriseModel.setUpdateDate(new Date());

                    // 只导入不存在的企业，根据统一认证的企业编号判断库中是否已经存在，若已经存在 过滤。
                    if (isExists(enterpriseModel)){
                        continue;
                    }

                }
                entityList.add(enterpriseModel);
            }
        if (entityList.size()==1000){
            queryEnterpriseService.saveBatch(entityList);
            entityList.clear();

        }
        queryEnterpriseService.saveBatch(entityList);
    }


    /**
     * 判断当前库中是否存在统一企业认证
     * @param enterpriseModel
     * @return
     */
    public boolean isExists(EnterpriseModel enterpriseModel){
        QueryWrapper<EnterpriseModel> wrapper = new QueryWrapper<EnterpriseModel>();
        wrapper.eq("enterprise_code",enterpriseModel.getEnterPriseCode());
        EnterpriseModel res =  queryEnterpriseService.getOne(wrapper);
        if (res!=null){
            return true;
        }
        return false;
    }


    @Override
    public void deleteEnterpriseData(EnterpriseModeVo enterpriseModeVo) throws BaseException {
        QueryWrapper<EnterpriseModel> wrapper = new QueryWrapper<EnterpriseModel>();

        if (enterpriseModeVo.getIds().size()>0){
            wrapper.in("id",enterpriseModeVo.getIds());
        }

        queryEnterpriseService.removeByIds(enterpriseModeVo.getIds());
    }

}

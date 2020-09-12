package com.mujun.mng.service;

import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.vo.EnterpriseModeVo;

import java.util.List;
import java.util.Map;

public interface IEnterpriseCountService {

    Map<String, Object> queryEnterprise (EnterpriseModeVo enterpriseModeVo) throws BaseException;

    void batchImport(List<String[]> list) throws BaseException;

    void deleteEnterpriseCountData (EnterpriseModeVo enterpriseModeVo) throws BaseException;

}

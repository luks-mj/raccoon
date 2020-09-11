package com.mujun.mng.service;

import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.vo.EnterpriseModeVo;

import java.util.List;
import java.util.Map;

public interface ICountryTaxationService {

    Map<String, Object> queryCountryTaxation (EnterpriseModeVo enterpriseModeVo) throws BaseException;

    void batchImport(List<String[]> list) throws BaseException;

    void deleteCountryTaxationData (EnterpriseModeVo enterpriseModeVo) throws BaseException;

}

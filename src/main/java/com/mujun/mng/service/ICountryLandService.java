package com.mujun.mng.service;

import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.vo.EnterpriseModeVo;

import java.util.List;
import java.util.Map;

public interface ICountryLandService {

      Map<String, Object> queryCountryLandData (EnterpriseModeVo countryLandModeVo) throws BaseException;

      void batchImport(List<String[]>  list) throws BaseException;

      void deleteCountryLandData (EnterpriseModeVo countryLandModeVo) throws BaseException;

}

package com.mujun.mng.service;

import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.vo.CountryLandModeVo;

import java.util.List;
import java.util.Map;

public interface ICountryLandService {

      Map<String, Object> queryCountryLandData (CountryLandModeVo countryLandModeVo) throws BaseException;

      void batchImport(List<String[]>  list) throws BaseException;

      void deleteCountryLandData (CountryLandModeVo countryLandModeVo) throws BaseException;

}

package com.mujun.mng.service;

import com.mujun.mng.commons.exception.BaseException;

import java.util.List;
import java.util.Map;

public interface ICountryLandService {

      Map<String, Object> queryCountryLandData () throws BaseException;

      void batchImport(List<String[]>  list) throws BaseException;
}

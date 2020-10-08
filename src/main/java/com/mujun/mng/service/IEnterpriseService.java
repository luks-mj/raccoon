package com.mujun.mng.service;

import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.vo.EnterpriseModeVo;

import java.util.List;
import java.util.Map;

public interface IEnterpriseService {

      Map<String, Object> queryEnterpriseData(EnterpriseModeVo enterpriseModeVo) throws BaseException;

      void batchImport(List<String[]> list, EnterpriseModeVo enterpriseModeVo) throws BaseException;

      void deleteEnterpriseData(EnterpriseModeVo enterpriseModeVo) throws BaseException;

}

package com.mujun.mng.service;

import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.model.ContryLandModel;

import java.util.List;

public interface ICountryLandService {

      List<ContryLandModel> queryCountryLandData () throws BaseException;
}

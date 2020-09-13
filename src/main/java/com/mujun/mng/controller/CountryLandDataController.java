package com.mujun.mng.controller;


import com.mujun.mng.commons.config.SrConstantMDA;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.commons.model.RestResult;
import com.mujun.mng.commons.utils.ExcelUtil;
import com.mujun.mng.service.impl.CountryLandServiceImpl;
import com.mujun.mng.vo.EnterpriseModeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "国土资源数据导入", tags = {"CountryLandDataController"},description ="国土资源管理")
@RestController
public class CountryLandDataController {

    private static Logger logger = LoggerFactory.getLogger(CountryLandDataController.class);

    @Autowired
    private CountryLandServiceImpl countryLandService;

    // 国土资源数据导入
    @ApiOperation(value = "国土资源数据批量导入", notes = "国土数据导入")
    @RequestMapping(value = "/countryLand/batchImport", method = RequestMethod.POST)
    public RestResult batchQueryInstType(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {
        RestResult result = new RestResult();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
           String fileName = file.getOriginalFilename();
            String str = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            if (str.toUpperCase().equals("XLS") || str.toUpperCase().equals("XLSX")) {
                InputStream is = file.getInputStream();
                int MAX_AMOUNT = SrConstantMDA.BATCH_INST_MAX_AMOUNT;
                List<String[]> reList = ExcelUtil.readOneCol(is, 1,false);
                if (reList == null || reList.size() == 0) {
                    result.setMeta(HttpStatus.BAD_REQUEST.value(),"导入失败");
                    result.setInfo(resultList);
                    return result;
                } else if (reList.size() > MAX_AMOUNT) {
                    result.setMeta(HttpStatus.BAD_REQUEST.value(),"文件导入一次最多支持" + MAX_AMOUNT + "条记录!");
                    result.setInfo(resultList);
                    return result;
                }
                countryLandService.batchImport(reList);
                result.setMeta(HttpStatus.OK.value(),"");
            }
        } catch (Exception e) {
            logger.debug("国土数据导入异常：{}", e.getMessage());
            result.setMeta(HttpStatus.BAD_REQUEST.value(),"文件导入失败:"+e.getMessage());
            result.setInfo(resultList);
        }
        return result;
    }


    // 国土资源数据导入
    @ApiOperation(value = "国土资源数据查询", notes = "国土数据查询")
    @RequestMapping(value = "/countryLand/queryAll", method = RequestMethod.GET)
    public RestResult queryCountryLandData( HttpServletRequest request,EnterpriseModeVo enterpriseModeVo) {
        RestResult result = new RestResult();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
         Map<String,Object> results =  countryLandService.queryCountryLandData(enterpriseModeVo);
          result.setInfo(results);
          result.setMeta(HttpStatus.OK.value(),"");
        } catch (BaseException e) {
            logger.debug("国土数据查询异常：{}", e.getMessage());
            result.setMeta(SrConstantMDA.INTF_RET_CODE_EXCEPTION,e.getMessage());
            result.setInfo(resultList);
        }
        return result;
    }

    // 国土资源数据删除
    @ApiOperation(value = "国土资源数据删除", notes = "国土数据删除")
    @RequestMapping(value = "/countryLand/deleteByCode", method = RequestMethod.DELETE)
    public RestResult deleteCountryLandData( HttpServletRequest request,EnterpriseModeVo enterpriseModeVo) {
        RestResult result = new RestResult();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            countryLandService.deleteCountryLandData(enterpriseModeVo);
            EnterpriseModeVo model = new EnterpriseModeVo();
            Map<String,Object> results =  countryLandService.queryCountryLandData(model);
            result.setInfo(results);
            result.setMeta(HttpStatus.OK.value(),"删除成功");
        } catch (BaseException e) {
            logger.debug("国土数据删除异常：{}", e.getMessage());
            result.setInfo(resultList);
            result.setMeta(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        }
        return result;
    }

}

package com.mujun.mng.controller;


import com.mujun.mng.commons.config.SrConstantMDA;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.commons.model.RestResult;
import com.mujun.mng.commons.utils.ExcelUtil;
import com.mujun.mng.service.impl.CountryTaxationServiceImpl;
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

@Api(value = "国税资源数据导入", tags = {"CountryTaxationDataController"},description ="国税资源管理")
@RestController
public class CountryTaxationDataController {

    private static Logger logger = LoggerFactory.getLogger(CountryLandDataController.class);


    @Autowired
    private CountryTaxationServiceImpl countryTaxationService;

    // 国税资源数据导入
    @ApiOperation(value = "国税资源数据批量导入", notes = "国税数据导入")
    @RequestMapping(value = "/countryTaxation/batchImport", method = RequestMethod.POST)
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
                    result.setCode(SrConstantMDA.INTF_RET_CODE_EXCEPTION);
                    result.setData(resultList);
                    result.setMessage("文件导入为空或者文件导入失败！");
                    return result;
                } else if (reList.size() > MAX_AMOUNT) {
                    result.setCode(SrConstantMDA.INTF_RET_CODE_EXCEPTION);
                    result.setData(resultList);
                    result.setMessage("文件导入一次最多支持" + MAX_AMOUNT + "条记录!");
                    return result;
                }
                countryTaxationService.batchImport(reList);
                result.setCode(HttpStatus.OK.value());
                result.setMessage("导入完成！");
            }
        } catch (Exception e) {
            logger.debug("国税数据导入异常：{}", e.getMessage());
            result.setCode(SrConstantMDA.INTF_RET_CODE_EXCEPTION);
            result.setData(resultList);
            result.setMessage("国税数据导入异常:"+e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "国税资源数据查询", notes = "国税数据查询")
    @RequestMapping(value = "/countryTaxation/queryAll", method = RequestMethod.GET)
    public RestResult queryCountryLandData( HttpServletRequest request,EnterpriseModeVo enterpriseModeVo) {
        RestResult result = new RestResult();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            Map<String,Object> results =  countryTaxationService.queryCountryTaxation(enterpriseModeVo);
            result.setData(results);
            result.setMessage("查询成功");
            result.setCode(HttpStatus.OK.value());
        } catch (BaseException e) {
            logger.debug("国税数据查询异常：{}", e.getMessage());
            result.setCode(SrConstantMDA.INTF_RET_CODE_EXCEPTION);
            result.setData(resultList);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    // 国税资源数据删除
    @ApiOperation(value = "国税资源数据删除", notes = "国税数据删除")
    @RequestMapping(value = "/countryTaxation/deleteByCode", method = RequestMethod.DELETE)
    public RestResult deleteCountryLandData( HttpServletRequest request,EnterpriseModeVo enterpriseModeVo) {
        RestResult result = new RestResult();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            countryTaxationService.deleteCountryTaxationData(enterpriseModeVo);
            EnterpriseModeVo model = new EnterpriseModeVo();
            Map<String,Object> results =  countryTaxationService.queryCountryTaxation(model);
            result.setData(results);
            result.setMessage("删除成功");
            result.setCode(HttpStatus.OK.value());
        } catch (BaseException e) {
            logger.debug("国税数据删除异常：{}", e.getMessage());
            result.setCode(SrConstantMDA.INTF_RET_CODE_EXCEPTION);
            result.setData(resultList);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}

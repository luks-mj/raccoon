package com.mujun.mng.controller;

import com.mujun.mng.service.impl.EnterpriseServiceImpl;
import com.mujun.mng.commons.config.SrConstantMDA;
import com.mujun.mng.commons.exception.BaseException;
import com.mujun.mng.commons.model.RestResult;
import com.mujun.mng.commons.utils.ExcelUtil;
import com.mujun.mng.commons.utils.HttpServiceUtils;
import com.mujun.mng.vo.EnterpriseModeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "企业数据导入", tags = {"EnterpriseDataController"},description ="企业数据管理")
@RestController
public class EnterpriseDataController {

    private static Logger logger = LoggerFactory.getLogger(EnterpriseDataController.class);

    @Autowired
    private EnterpriseServiceImpl enterpriseService;

    // 国土资源数据导入
    @ApiOperation(value = "企业数据批量导入", notes = "企业数据导入")
    @RequestMapping(value = "/enterprise/batchImport", method = RequestMethod.POST)
    public RestResult batchQueryInstType(@RequestParam("file") MultipartFile file, Model model,  HttpServletRequest req ) {
        RestResult result = new RestResult();
        EnterpriseModeVo enterpriseModeVo=new EnterpriseModeVo();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            String year = null;//年
            String month = null; //月
           String fileName = file.getOriginalFilename();
            Map<String, Object> params = HttpServiceUtils.getParamsFromReq(req);
            if(params.containsKey("year")){
                year = params.get("year").toString();
                enterpriseModeVo.setYear(Integer.parseInt(year));
            }
            if(params.containsKey("month")){
                month = params.get("month").toString();
                enterpriseModeVo.setMonth(Integer.parseInt(month));
            }
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
                enterpriseService.batchImport(reList,enterpriseModeVo);
                result.setMeta(HttpStatus.OK.value(),"");
            }
        } catch (Exception e) {
            logger.debug("企业数据导入异常：{}", e.getMessage());
            result.setMeta(HttpStatus.BAD_REQUEST.value(),"文件导入失败:"+e.getMessage());
            result.setInfo(resultList);
        }
        return result;
    }


    // 企业数据导入
    @ApiOperation(value = "企业数据查询", notes = "企业数据查询")
    @RequestMapping(value = "/enterprise/queryAll", method = RequestMethod.GET)
    public RestResult queryEnterpriseData( HttpServletRequest request,EnterpriseModeVo enterpriseModeVo) {
        RestResult result = new RestResult();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
         Map<String,Object> results =  enterpriseService.queryEnterpriseData(enterpriseModeVo);
          result.setInfo(results);
          result.setMeta(HttpStatus.OK.value(),"");
        } catch (BaseException e) {
            logger.debug("国土数据查询异常：{}", e.getMessage());
            result.setMeta(SrConstantMDA.INTF_RET_CODE_EXCEPTION,e.getMessage());
            result.setInfo(resultList);
        }
        return result;
    }

    // 企业数据删除
    @ApiOperation(value = "企业数据删除", notes = "企业数据删除")
    @RequestMapping(value = "/enterprise/deleteByCode", method = RequestMethod.DELETE)
    public RestResult deleteEnterpriseData( HttpServletRequest request, @RequestBody EnterpriseModeVo enterpriseModeVo) {
        RestResult result = new RestResult();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            enterpriseService.deleteEnterpriseData(enterpriseModeVo);
            EnterpriseModeVo model = new EnterpriseModeVo();
            Map<String,Object> results =  enterpriseService.queryEnterpriseData(model);
            result.setInfo(results);
            result.setMeta(HttpStatus.OK.value(),"删除成功");
        } catch (BaseException e) {
            logger.debug("企业数据删除异常：{}", e.getMessage());
            result.setInfo(resultList);
            result.setMeta(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        }
        return result;
    }

}

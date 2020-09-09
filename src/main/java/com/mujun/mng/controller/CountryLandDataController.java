package com.mujun.mng.controller;


import com.mujun.mng.commons.config.SrConstantMDA;
import com.mujun.mng.commons.model.RestResult;
import com.mujun.mng.commons.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "国土资源数据导入", tags = {"CountryLandDataController"})
@RestController
public class CountryLandDataController {

    private static Logger logger = LoggerFactory.getLogger(CountryLandDataController.class);

    // 国土资源数据导入
    @ApiOperation(value = "国土资源数据批量导入", notes = "国土数据导入")
    @RequestMapping(value = "/countryLand/batchImport", method = RequestMethod.POST)
    public RestResult batchQueryInstType(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {
        RestResult result = new RestResult();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            int MAX_AMOUNT = SrConstantMDA.BATCH_INST_MAX_AMOUNT;
            List<String> reList = ExcelUtil.readOneCol(file, false);
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



        } catch (Exception e) {
            logger.debug("国土数据导入异常：{}", e.getMessage());
            result.setCode(SrConstantMDA.INTF_RET_CODE_EXCEPTION);
            result.setData(resultList);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}

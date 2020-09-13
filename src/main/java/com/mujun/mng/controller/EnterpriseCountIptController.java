package com.mujun.mng.controller;


import com.mujun.mng.commons.config.SrConstantMDA;
import com.mujun.mng.commons.model.RestResult;
import com.mujun.mng.commons.utils.ExcelUtil;
import com.mujun.mng.service.impl.EnterpriseCountServiceImpl;
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

@Api(value = "企业统计数据导入", tags = {"EnterpriseCountIptController"},description ="企业统计数据导入")
@RestController
public class EnterpriseCountIptController {

    private static Logger logger = LoggerFactory.getLogger(CountryLandDataController.class);

    @Autowired
    private EnterpriseCountServiceImpl enterpriseCountService;

    // 国税资源数据导入
    @ApiOperation(value = "企业统计数据导入", notes = "企业统计数据导入")
    @RequestMapping(value = "/enterpriseCount/batchImport", method = RequestMethod.POST)
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
                    result.setInfo(resultList);
                    result.setMeta(HttpStatus.BAD_REQUEST.value(),"文件导入为空或者文件导入失败！");
                    return result;
                } else if (reList.size() > MAX_AMOUNT) {
                    result.setInfo(resultList);
                    result.setMeta(HttpStatus.BAD_REQUEST.value(),"文件导入一次最多支持" + MAX_AMOUNT + "条记录!");
                    return result;
                }
                enterpriseCountService.batchImport(reList);
                result.setMeta(HttpStatus.OK.value(),"导入完成！");
            }
        } catch (Exception e) {
            logger.debug("企业统计数据导入异常：{}", e.getMessage());
            result.setInfo(resultList);
            result.setMeta(HttpStatus.BAD_REQUEST.value(),"企业统计数据导入异常:"+e.getMessage());
        }
        return result;
    }



}

package com.mujun.mng.version;

import com.alibaba.fastjson.JSON;
import com.mujun.mng.commons.utils.DateUtil;
import com.mujun.mng.commons.utils.ExceptionUtils;
import com.mujun.mng.model.VersionModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Api(value="版本拨测类",tags={"VersionController"},description = "版本查询控制类")
@RestController
public class VersionController {


    private String startTime = DateUtil.getNowTime();

    @GetMapping("getStartTime")
    public String getStartTime() {
        return startTime;
    }


    @ApiOperation(value = "getVersion", notes = "getVersion")
    @GetMapping("getVersion")
    public String getVersion() {
        Map<String, Object> returnMap = new HashMap<>();
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String name = this.getClass().getName();
        String separator = null;
        if(path.contains("/")) {
            separator = "/";
        }
        if(path.contains("\\")) {
            separator = "\\\\";
        }
        name = name.replaceAll("\\.", separator);
        File f = new File(path + name + ".class");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        returnMap.put("version", sd.format(f.lastModified()));
        return JSON.toJSONString(returnMap);
    }

    @ApiOperation(value = "getConstant", notes = "getConstant")
    @PostMapping("getConstant")
    public Object getConstant(@RequestBody VersionModel versionModel) {
        Map r = new HashMap();
        try {
            Class c = Class.forName(versionModel.getClazz());
            Field f = c.getDeclaredField(versionModel.getField());
            Object classObject = null;
            versionModel.setFieldValue(f.get(classObject));
        } catch (Exception e) {
            versionModel.setFieldValue(ExceptionUtils.getStackTrace(e));
        }
        return versionModel;
    }

}

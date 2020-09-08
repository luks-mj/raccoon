package com.mujun.mng.commons.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

@JsonSerialize
public class RestResult<T> implements Serializable {
    protected static final Logger LOGGER = LoggerFactory.getLogger(RestResult.class);
    @JSONField(
            ordinal = 1
    )
    private int code;
    @JSONField(
            ordinal = 2
    )
    private String message;
    @JSONField(
            ordinal = 3
    )
    private T data;

    public RestResult() {
    }

    public RestResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return StringUtils.trimToEmpty(this.message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "RestResult(code=" + this.code + ", message=" + this.message + ", data=" + JSON.toJSONString(this.data) + ")";
    }
}
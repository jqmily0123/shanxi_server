package com.shanxi.water.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@NoArgsConstructor
@Data
public class BaseResponse implements Serializable {
    /**
     * 状态码
     */
    private int code;

    /**
     * 数据
     */
    private Object data;

    /**
     * 状态码信息
     */
    private String message;

    /**
     * 状态码描述（详情）
     */
    private String description;

    public BaseResponse(int code, Object data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, Object data, String message){
        this(code, data,message,"");
    }
    public BaseResponse(int code, Object data){
        this(code, data, "", "");
    }
    public BaseResponse(int code,String message){
        this(code,"-",message);
    }
}


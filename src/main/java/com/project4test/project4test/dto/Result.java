package com.project4test.project4test.dto;

import com.project4test.project4test.enums.ResultCode;
import com.project4test.project4test.enums.SaTokenExceptionEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * 统一API响应结果封装类（Lombok版）
 */
@Data
@Accessors(chain = true) // 支持链式调用
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // 状态码
    private int code;
    // 消息
    private String msg;
    // 数据
    private T data;
    // 时间戳
    private long timestamp = System.currentTimeMillis();

    // 成功静态方法
    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS.getCode())
                .setMsg(ResultCode.SUCCESS.getMsg())
                .setData(data);
    }

    //saToken异常静态工厂方法
    public static <T> Result<T> SaResult(SaTokenExceptionEnum saTokenExceptionEnum) {
        return new Result<T>()
                .setCode(saTokenExceptionEnum.getCode())
                .setMsg(saTokenExceptionEnum.getMsg())
                .setData(null);
    }

    // 失败静态方法
    public static <T> Result<T> fail() {
        return fail(ResultCode.FAILED);
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        return fail(resultCode.getCode(), resultCode.getMsg());
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<T>()
                .setCode(code)
                .setMsg(msg);
    }
}
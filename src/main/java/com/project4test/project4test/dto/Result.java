package com.project4test.project4test.dto;

import com.project4test.project4test.enums.ResultCode;
import com.project4test.project4test.enums.SaTokenExceptionEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.lang.reflect.Method;

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

    // 使用反射实现 ObjectResult 方法
    public static <T> Result<T> ObjectResult(Object data) {
        Result<T> result = new Result<>();
        if (data != null) {
            try {
                // 反射调用 getCode 方法
                Method getCodeMethod = data.getClass().getMethod("getCode");
                int code = (int) getCodeMethod.invoke(data);
                result.setCode(code);

                // 反射调用 getMsg 方法
                Method getMsgMethod = data.getClass().getMethod("getMsg");
                String msg = (String) getMsgMethod.invoke(data);
                result.setMsg(msg);
            } catch (Exception e) {
                // 处理反射调用异常，设置默认失败状态
                result.setCode(ResultCode.FAILED.getCode());
                result.setMsg("获取 code 和 msg 失败: " + e.getMessage());
            }
        } else {
            // 若 data 为 null，设置默认失败状态
            result.setCode(ResultCode.FAILED.getCode());
            result.setMsg("传入数据为 null");
        }
        result.setData(null);
        return result;
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
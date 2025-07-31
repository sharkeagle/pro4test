package com.project4test.project4test.exception;


import cn.dev33.satoken.exception.SaTokenException;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.enums.SaTokenExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SaTokenException.class)
    public Result<String> handlerSaTokenException(SaTokenException e) {
        log.info("SaTokenCode: {}", e.getCode());
        // 根据不同异常细分状态码返回不同的提示
        if(e.getCode() == SaTokenExceptionEnum.TOKEN_NOT_FOUND.getCode()) {
            return Result.SaResult(SaTokenExceptionEnum.TOKEN_NOT_FOUND);
        }


        // 默认的提示
        return Result.fail(e.getCode(),"服务器繁忙，请稍后重试...");
    }
}

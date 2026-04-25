package com.yinnnh.handler;

import com.yinnnh.common.Result;
import com.yinnnh.common.ResultEnum;
import com.yinnnh.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截自定义业务异常
     */
    @ExceptionHandler(BizException.class)
    public Result<Void> handleBizException(BizException e) {
        log.warn("业务异常：code={}, msg={}", e.getCode(), e.getMsg());
        // 直接用你 Result 类的 error(Integer code, String msg)
        return Result.error(e.getCode(), e.getMsg());
    }

    /**
     * 拦截参数校验异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Result<Void> handleValidException(Exception e) {
        String msg = "参数校验失败";
        if (e instanceof MethodArgumentNotValidException) {
            msg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        } else if (e instanceof BindException) {
            msg = ((BindException) e).getBindingResult().getFieldError().getDefaultMessage();
        }
        log.warn("参数校验异常：{}", msg);
        // 直接用你 Result 类的 error(ResultEnum)
        return Result.error(ResultEnum.PARAM_ERROR);
    }

    /**
     * 拦截所有其他异常（兜底）
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常：", e);
        // 直接用你 Result 类的 error(ResultEnum)
        return Result.error(ResultEnum.SERVER_ERROR);
    }
}
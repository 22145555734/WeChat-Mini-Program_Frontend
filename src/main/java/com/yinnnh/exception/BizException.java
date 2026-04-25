package com.yinnnh.exception;

import com.yinnnh.common.ResultEnum;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final Integer code;
    private final String msg;

    // 直接传枚举的构造方法（最常用）
    public BizException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    // 也可以直接传 code 和 msg
    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
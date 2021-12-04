package com.rick.lottery.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 统一返回对象中 Code码 Info描述
 * @create 2021-11-27 23:18
 **/
@Data
@AllArgsConstructor
public class Result implements Serializable {

    private String code;
    private String info;

    public static Result buildResult(String code, String info) {
        return new Result(code, info);
    }

    public static Result buildSuccessResult() {
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static Result buildErrorResult() {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }
}

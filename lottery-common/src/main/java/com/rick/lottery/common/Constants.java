package com.rick.lottery.common;


import lombok.Getter;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 枚举信息定义
 * @create 2021-11-27 23:06
 **/
public class Constants {

    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知错误"),
        ILLEGAL_PARAMETER("0002","非法参数"),
        INDEX_DUP("0003","主键冲突")

        ;
        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        @Getter
        private String code;
        @Getter
        private String info;
    }
}

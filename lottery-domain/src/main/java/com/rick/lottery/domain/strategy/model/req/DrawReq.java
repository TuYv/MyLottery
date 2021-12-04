package com.rick.lottery.domain.strategy.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description
 * @create 2021-11-29 23:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrawReq {
    //用户Id
    private String uId;
    //策略Id
    private Long strategyId;
}

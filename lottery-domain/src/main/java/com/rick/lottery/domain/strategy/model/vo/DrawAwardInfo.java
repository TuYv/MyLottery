package com.rick.lottery.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 中奖奖品信息
 * @create 2021-11-29 23:57
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrawAwardInfo {

    /**
     * 奖品ID
     */
    private String rewardId;

    /**
     * 奖品名称
     */
    private String awardName;

}

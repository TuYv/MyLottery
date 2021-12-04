package com.rick.lottery.domain.strategy.model.vo;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 奖品概率信息，奖品编号、库存、概率
 * @create 2021-11-29 23:57
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardRateInfo {
    // 奖品ID
    private String awardId;

    // 中奖概率
    private BigDecimal awardRate;

}

package com.rick.lottery.domain.strategy.model.aggregates;

import com.rick.lottery.infrastructure.po.Strategy;
import com.rick.lottery.infrastructure.po.StrategyDetail;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description
 * @create 2021-11-29 23:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StrategyRich {
    //策略ID
    private Long strategyId;
    // 策略配置
    private Strategy strategy;
    // 策略明细
    private List<StrategyDetail> strategyDetailList;
}

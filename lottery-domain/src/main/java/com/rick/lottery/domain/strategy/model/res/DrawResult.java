package com.rick.lottery.domain.strategy.model.res;

import com.rick.lottery.common.Constants;
import com.rick.lottery.domain.strategy.model.vo.DrawAwardInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 抽奖结果
 * @create 2021-11-29 23:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrawResult {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 中奖状态：0未中奖、1已中奖、2兜底奖 Constants.DrawState
     */
    private Integer drawState = Constants.DrawState.FAIL.getCode();

    /**
     * 中奖奖品信息
     */
    private DrawAwardInfo drawAwardInfo;

    public DrawResult(String uId, Long strategyId, Integer drawState) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }
}


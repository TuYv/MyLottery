package com.rick.lottery.domain.strategy.service.algorithm.impl;

import com.rick.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.rick.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 必中奖策略抽奖 排除已经中奖的概率 重新计算中奖范围
 * @create 2021-12-04 22:29
 **/
@Component("entiretyRateRandomDrawAlgorithm")
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        //排除掉不在抽奖范围的奖品ID集合
        List<AwardRateInfo> differenceAwardRateInfoList = new ArrayList<>();
        List<AwardRateInfo> awardRateIntervalValList = awardRateInfoMap.get(strategyId);

        for (AwardRateInfo awardRateInfo : awardRateIntervalValList) {
            String awardId = awardRateInfo.getAwardId();
            if (excludeAwardIds.contains(awardId)) {
                continue;
            }
            differenceAwardRateInfoList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        //前置判断：奖品列表为0 返回NULL
        if (differenceAwardRateInfoList.size() == 0) { return null;}

        //前置判断：奖品列表为1 直接返回
        if (differenceAwardRateInfoList.size() == 1) { return differenceAwardRateInfoList.get(0).getAwardId();}

        int randomVal = this.generateSecureRandomIntCode(100);

        //循环获取奖品
        String awardId = null;
        int cursorVal = 0;
        for (AwardRateInfo awardRateInfo : differenceAwardRateInfoList) {
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if (randomVal <= (cursorVal + rateVal)) {
                awardId = awardRateInfo.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }

        //返回中奖结果
        return awardId;

    }
}

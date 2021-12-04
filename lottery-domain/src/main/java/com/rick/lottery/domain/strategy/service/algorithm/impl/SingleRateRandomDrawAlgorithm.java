package com.rick.lottery.domain.strategy.service.algorithm.impl;

import com.rick.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖
 * @create 2021-12-04 22:58
 **/
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        //获取策略对应的元祖
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        //随机索引
        int randomVal = this.generateSecureRandomIntCode(100);
        int idx = super.hashIdx(randomVal);

        //返回结果
        String awardId = rateTuple[idx];

        //如果中奖ID命中排除奖品列表，则返回NULL
        return excludeAwardIds.contains(awardId) ? null : awardId;
    }
}

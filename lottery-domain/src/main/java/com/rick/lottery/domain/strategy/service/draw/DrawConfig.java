package com.rick.lottery.domain.strategy.service.draw;

import com.rick.lottery.common.Constants;
import com.rick.lottery.common.Constants.StrategyMode;
import com.rick.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 抽奖统一配置信息类
 * @create 2021-12-04 23:05
 **/
public class DrawConfig {

    @Resource
    private IDrawAlgorithm entiretyRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    /** 抽奖策略组 */
    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmGroup = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmGroup.put(StrategyMode.ENTIRETY.getCode(), entiretyRateRandomDrawAlgorithm);
        drawAlgorithmGroup.put(StrategyMode.SINGLE.getCode(), singleRateRandomDrawAlgorithm);
    }
}

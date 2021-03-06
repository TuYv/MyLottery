package com.rick.lottery.domain.strategy.service.draw;

import com.rick.lottery.common.Constants.DrawState;
import com.rick.lottery.common.Constants.StrategyMode;
import com.rick.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.rick.lottery.domain.strategy.model.req.DrawReq;
import com.rick.lottery.domain.strategy.model.res.DrawResult;
import com.rick.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.rick.lottery.domain.strategy.model.vo.DrawAwardInfo;
import com.rick.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.rick.lottery.infrastructure.po.Award;
import com.rick.lottery.infrastructure.po.Strategy;
import com.rick.lottery.infrastructure.po.StrategyDetail;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 定义抽象抽奖过程，模版模式
 * @create 2021-12-04 23:12
 **/
@Slf4j
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec{

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        //1. 获取抽奖策略
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();

        //2.校验抽奖策略是否已经初始化到内存
        this.checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyRich.getStrategyDetailList());

        //3. 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等
        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());

        //4. 执行抽奖算法
        String awardId = this.drawAlgorithm(req.getStrategyId(), drawAlgorithmGroup.get(strategy.getStrategyMode()), excludeAwardIds);

        //5.包装中奖结果
        return buildDrawResult(req.getUId(), req.getStrategyId(), awardId);
    }

    /**
     * 包装抽奖结果
     * @param uId
     * @param strategyId
     * @param awardId     奖品ID，null 情况：并发抽奖情况下，库存临界值1 -> 0，会有用户中奖结果为 null
     * @return
     */
    protected DrawResult buildDrawResult(String uId, Long strategyId, String awardId) {
        if (null == awardId) {
            log.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, DrawState.FAIL.getCode());
        }

        Award award = super.queryAwardInfoByAwardId(awardId);
        DrawAwardInfo drawAwardInfo = new DrawAwardInfo(award.getAwardId(), award.getAwardName());
        log.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());

        return new DrawResult(uId, strategyId, DrawState.SUCCESS.getCode(), drawAwardInfo);
    }

    /**
     * 执行抽奖算法
     * @param strategyId
     * @param iDrawAlgorithm
     * @param excludeAwardIds
     * @return
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm iDrawAlgorithm, List<String> excludeAwardIds);

    /**
     * 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等，这类数据是含有业务逻辑的，所以需要由具体的实现方决定
     * @param strategyId
     * @return
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     * 校验抽奖策略是否已经初始化到内存
     * @param strategyId
     * @param strategyMode
     * @param strategyDetailList
     */
    private void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList) {
        // 非单项概率，不必存入缓存
        if (!StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }

        IDrawAlgorithm drawAlgorithm = drawAlgorithmGroup.get(strategyMode);
        //已初始化过的数据 不必重复初始化
        if (drawAlgorithm.isExistRateTuple(strategyId)) {
            return;
        }

        // 解析并初始化中奖概率数据到散列表
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetail strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }
}

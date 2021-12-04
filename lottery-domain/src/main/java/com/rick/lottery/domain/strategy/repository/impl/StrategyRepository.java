package com.rick.lottery.domain.strategy.repository.impl;

import com.rick.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.rick.lottery.domain.strategy.repository.IStrategyRepository;
import com.rick.lottery.infrastructure.dao.IAwardDao;
import com.rick.lottery.infrastructure.dao.IStrategyDao;
import com.rick.lottery.infrastructure.dao.IStrategyDetailDao;
import com.rick.lottery.infrastructure.po.Award;
import com.rick.lottery.infrastructure.po.Strategy;
import com.rick.lottery.infrastructure.po.StrategyDetail;
import java.util.List;
import javax.annotation.Resource;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 策略表仓储服务
 * @create 2021-11-30 00:02
 **/
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> list = strategyDetailDao.queryStrategyDetailList(strategyId);
        return new StrategyRich(strategyId, strategy, list);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail strategyDetail = new StrategyDetail();
        strategyDetail.setStrategyId(strategyId);
        strategyDetail.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(strategyDetail);
        return count == 1;
    }
}

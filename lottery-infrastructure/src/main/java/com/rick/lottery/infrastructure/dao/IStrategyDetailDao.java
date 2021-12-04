package com.rick.lottery.infrastructure.dao;

import com.rick.lottery.infrastructure.po.StrategyDetail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 策略表数据库操作
 * @author：小傅哥，微信：fustack
 * @date: 2021/8/28
 * @Copyright：公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Mapper
public interface IStrategyDetailDao {

    /**
     * 查询策略表详细配置
     * @param strategyId 策略ID
     * @return           返回结果
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 查询无库存策略奖品ID
     * @param strategyId 策略ID
     * @return           返回结果
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyDetailReq 策略ID、奖品ID
     * @return                  返回结果
     */
    int deductStock(StrategyDetail strategyDetailReq);

}

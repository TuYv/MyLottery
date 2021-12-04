package com.rick.lottery.domain.strategy.service.draw;

import com.rick.lottery.domain.strategy.model.req.DrawReq;
import com.rick.lottery.domain.strategy.model.res.DrawResult;

/**
 * 抽奖执行接口
 */
public interface IDrawExec {

    /**
     * 抽奖方法
     * @param req 抽奖参数： 用户ID 参数ID
     * @return
     */
    DrawResult doDrawExec(DrawReq req);
}

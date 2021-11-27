package com.rick.lottery.interfaces;

import com.rick.lottery.common.Constants;
import com.rick.lottery.common.Constants.ResponseCode;
import com.rick.lottery.common.Result;
import com.rick.lottery.infrastructure.dao.IActivityDao;
import com.rick.lottery.infrastructure.po.Activity;
import com.rick.lottery.rpc.IActivityBooth;
import com.rick.lottery.rpc.dto.ActivityDto;
import com.rick.lottery.rpc.req.ActivityReq;
import com.rick.lottery.rpc.res.ActivityRes;
import javax.annotation.Resource;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description 活动展台
 * @create 2021-11-27 23:29
 **/
public class ActivityBooth implements IActivityBooth {

    @Resource private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = ActivityDto.builder()
            .activityId(activity.getId())
            .activityName(activity.getActivityName())
            .activityDesc(activity.getActivityDesc())
            .beginDateTime(activity.getBeginDateTime())
            .endDateTime(activity.getEndDateTime())
            .stockCount(activity.getStockCount())
            .takeCount(activity.getTakeCount())
            .build();

        return new ActivityRes(Result.buildSuccessResult(), activityDto);
    }
}

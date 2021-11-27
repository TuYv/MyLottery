package com.rick.lottery.rpc.res;

import com.rick.lottery.common.Result;
import com.rick.lottery.rpc.dto.ActivityDto;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author Max.Tu
 * @program MyLottery
 * @description
 * @create 2021-11-27 23:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ActivityRes implements Serializable {

    private Result result;
    private ActivityDto activity;
}

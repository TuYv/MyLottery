package com.rick.lottery.infrastructure.dao;

import com.rick.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@Mapper
public interface IAwardDao {

    Award queryAwardInfo(String awardId);

}

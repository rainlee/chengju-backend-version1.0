package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.EventDO;
import com.example.chengjubackend.demos.mybatis.entity.ParticipateDO;
import com.example.chengjubackend.demos.mybatis.mapper.ParticipateDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * 活动参与Service的实现类
 * @author Jilin He
 * @date 2020.01.17
 */

@Service
public class ParticipateDOServiceImpl implements ParticipateDOService {

    @Autowired
    private ParticipateDOMapper participateDOMapper;

    @Override
    public ResultDO getParticipatedEvents(Integer userId) {
        if (userId == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入的学号不能为空或0");
        }
        List<EventDO> list = participateDOMapper.getParticipatedEvents(userId);
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "该用户未参与任何活动。");
        }
        return new ResultDO((HttpCode.SUCCESS.getCode()), "以下是该用户参与的活动。", list);
    }

    @Override
    public ResultDO insertParticipate(ParticipateDO participateDO) {
        int influenceLines = participateDOMapper.insertParticipate(participateDO);
        System.out.println(influenceLines);
        if (influenceLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "添加失败。", influenceLines);
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), "添加成功", influenceLines);
    }

    @Override
    public ResultDO deleteParticipated(Integer eventId, Integer userId) {
        int influenceLines = participateDOMapper.deleteParticipated(eventId, userId);
        if (influenceLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "删除失败。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), "删除成功", influenceLines);
    }
}
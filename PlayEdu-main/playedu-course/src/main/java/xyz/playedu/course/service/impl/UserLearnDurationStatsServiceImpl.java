/*
 * Copyright (C) 2023 杭州白书科技有限公司
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.playedu.course.service.impl;

import cn.hutool.core.date.DateTime;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.SneakyThrows;

import org.springframework.stereotype.Service;

import xyz.playedu.course.domain.UserLearnDurationStats;
import xyz.playedu.course.mapper.UserLearnDurationStatsMapper;
import xyz.playedu.course.service.UserLearnDurationStatsService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author tengteng
 * @description 针对表【user_learn_duration_stats】的数据库操作Service实现
 * @createDate 2023-03-22 13:55:29
 */
@Service
public class UserLearnDurationStatsServiceImpl
        extends ServiceImpl<UserLearnDurationStatsMapper, UserLearnDurationStats>
        implements UserLearnDurationStatsService {

    @Override
    @SneakyThrows
    public void storeOrUpdate(Integer userId, Long startTime, Long endTime) {
        String date = new DateTime().toDateStr();
        Long duration = endTime - startTime;

        UserLearnDurationStats stats =
                getOne(query().getWrapper().eq("user_id", userId).eq("created_date", date));
        if (stats == null) {
            UserLearnDurationStats newStats = new UserLearnDurationStats();
            newStats.setUserId(userId);
            newStats.setDuration(duration);
            newStats.setCreatedDate(new DateTime(date));
            save(newStats);
            return;
        }

        UserLearnDurationStats newStats = new UserLearnDurationStats();
        newStats.setId(stats.getId());
        newStats.setDuration(stats.getDuration() + duration);
        updateById(newStats);
    }

    @Override
    @SneakyThrows
    public Long todayTotal() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        return count(query().getWrapper().eq("created_date", today));
    }

    @Override
    @SneakyThrows
    public Long yesterdayTotal() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = simpleDateFormat.format(new Date(System.currentTimeMillis() - 86399000));
        return count(query().getWrapper().eq("created_date", yesterday));
    }

    @Override
    public List<UserLearnDurationStats> top10() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        return list(
                query().getWrapper()
                        .eq("created_date", today)
                        .orderByDesc("duration")
                        .last("limit 10"));
    }

    @Override
    public Long todayUserDuration(Integer userId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        UserLearnDurationStats stats =
                getOne(query().getWrapper().eq("user_id", userId).eq("created_date", today));
        if (stats == null) {
            return 0L;
        }
        return stats.getDuration();
    }

    @Override
    public Long userDuration(Integer userId) {
        Long totalDuration = getBaseMapper().getUserDuration(userId);
        return totalDuration == null ? 0L : totalDuration;
    }

    @Override
    public List<UserLearnDurationStats> dateBetween(Integer userId, String startAt, String endAt) {
        return list(
                query().getWrapper().eq("user_id", userId).between("created_date", startAt, endAt));
    }

    @Override
    public void remove(Integer userId) {
        remove(query().getWrapper().eq("user_id", userId));
    }
}

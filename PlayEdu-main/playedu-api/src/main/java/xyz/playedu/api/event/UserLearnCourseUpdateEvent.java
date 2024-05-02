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
package xyz.playedu.api.event;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.ApplicationEvent;

/**
 * @Author 杭州白书科技有限公司
 *
 * @create 2023/3/22 14:14
 */
@Setter
@Getter
public class UserLearnCourseUpdateEvent extends ApplicationEvent {

    private Integer userId;
    private Integer courseId;
    private Integer hourId;
    private Long startAt;
    private Long endAt;

    public UserLearnCourseUpdateEvent(
            Object source,
            Integer userId,
            Integer courseId,
            Integer hourId,
            Long startTime,
            Long endTime) {
        super(source);
        this.userId = userId;
        this.courseId = courseId;
        this.hourId = hourId;
        this.startAt = startTime;
        this.endAt = endTime;
    }
}

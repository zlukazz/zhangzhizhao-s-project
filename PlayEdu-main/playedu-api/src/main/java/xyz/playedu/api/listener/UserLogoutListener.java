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
package xyz.playedu.api.listener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import xyz.playedu.api.event.UserLogoutEvent;
import xyz.playedu.common.service.UserLoginRecordService;

/**
 * @Author 杭州白书科技有限公司
 *
 * @create 2023/3/21 14:51
 */
@Component
@Slf4j
public class UserLogoutListener {

    @Autowired private UserLoginRecordService userLoginRecordService;

    @Async
    @EventListener
    public void updateLoginRecord(UserLogoutEvent event) {
        userLoginRecordService.logout(event.getUserId(), event.getJti());
    }
}

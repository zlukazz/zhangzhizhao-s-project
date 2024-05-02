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
package xyz.playedu.common.service;

import com.baomidou.mybatisplus.extension.service.IService;

import xyz.playedu.common.domain.UserDepartment;

import java.util.List;

/**
 * @author tengteng
 * @description 针对表【user_department】的数据库操作Service
 * @createDate 2023-02-23 15:08:38
 */
public interface UserDepartmentService extends IService<UserDepartment> {
    List<Integer> getUserIdsByDepIds(List<Integer> depIds);

    void storeDepIds(Integer userId, Integer[] depIds);

    void resetStoreDepIds(Integer userId, Integer[] depIds);
}

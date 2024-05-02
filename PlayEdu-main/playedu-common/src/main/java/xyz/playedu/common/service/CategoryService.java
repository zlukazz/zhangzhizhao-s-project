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

import xyz.playedu.common.domain.Category;
import xyz.playedu.common.exception.NotFoundException;

import java.util.List;
import java.util.Map;

/**
 * @author tengteng
 * @description 针对表【resource_categories】的数据库操作Service
 * @createDate 2023-02-23 09:50:18
 */
public interface CategoryService extends IService<Category> {

    List<Category> listByParentId(Integer id);

    List<Category> all();

    Category findOrFail(Integer id) throws NotFoundException;

    void deleteById(Integer id) throws NotFoundException;

    void update(Category category, String name, Integer parentId, Integer sort)
            throws NotFoundException;

    void create(String name, Integer parentId, Integer sort) throws NotFoundException;

    String childrenParentChain(Category category);

    String compParentChain(Integer parentId) throws NotFoundException;

    void resetSort(List<Integer> ids);

    void changeParent(Integer id, Integer parentId, List<Integer> ids) throws NotFoundException;

    Map<Integer, List<Category>> groupByParent();

    Map<Integer, String> id2name();

    Long total();
}

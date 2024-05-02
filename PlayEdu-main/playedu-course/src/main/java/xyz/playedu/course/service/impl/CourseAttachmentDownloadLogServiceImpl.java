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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import xyz.playedu.common.types.paginate.CourseAttachmentDownloadLogPaginateFiler;
import xyz.playedu.common.types.paginate.PaginationResult;
import xyz.playedu.course.domain.CourseAttachmentDownloadLog;
import xyz.playedu.course.mapper.CourseAttachmentDownloadLogMapper;
import xyz.playedu.course.service.CourseAttachmentDownloadLogService;

@Service
public class CourseAttachmentDownloadLogServiceImpl
        extends ServiceImpl<CourseAttachmentDownloadLogMapper, CourseAttachmentDownloadLog>
        implements CourseAttachmentDownloadLogService {
    @Override
    public PaginationResult<CourseAttachmentDownloadLog> paginate(
            int page, int size, CourseAttachmentDownloadLogPaginateFiler filter) {
        filter.setPageStart((page - 1) * size);
        filter.setPageSize(size);

        PaginationResult<CourseAttachmentDownloadLog> pageResult = new PaginationResult<>();
        pageResult.setData(getBaseMapper().paginate(filter));
        pageResult.setTotal(getBaseMapper().paginateCount(filter));

        return pageResult;
    }
}

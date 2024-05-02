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
package xyz.playedu.api.request.backend;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.util.List;

@Data
public class CourseAttachmentMultiRequest {
    @Data
    public static class AttachmentItem {
        private String title;
        private Integer sort;
        private String type;
        private Integer rid;
    }

    @NotNull(message = "attachments参数不存在")
    private List<AttachmentItem> attachments;
}

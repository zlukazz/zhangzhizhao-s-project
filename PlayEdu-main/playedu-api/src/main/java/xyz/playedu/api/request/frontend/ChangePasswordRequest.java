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
package xyz.playedu.api.request.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @Author 杭州白书科技有限公司
 *
 * @create 2023/3/13 09:22
 */
@Data
public class ChangePasswordRequest {

    @NotBlank(message = "请输入原密码")
    @JsonProperty("old_password")
    private String oldPassword;

    @NotBlank(message = "请输入新密码")
    @JsonProperty("new_password")
    private String newPassword;
}

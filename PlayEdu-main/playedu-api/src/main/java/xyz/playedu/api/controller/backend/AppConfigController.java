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
package xyz.playedu.api.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import xyz.playedu.api.request.backend.AppConfigRequest;
import xyz.playedu.common.annotation.BackendPermission;
import xyz.playedu.common.annotation.Log;
import xyz.playedu.common.constant.BPermissionConstant;
import xyz.playedu.common.constant.BusinessTypeConstant;
import xyz.playedu.common.constant.ConfigConstant;
import xyz.playedu.common.constant.SystemConstant;
import xyz.playedu.common.domain.AppConfig;
import xyz.playedu.common.service.AppConfigService;
import xyz.playedu.common.types.JsonResponse;
import xyz.playedu.common.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/backend/v1/app-config")
public class AppConfigController {

    @Autowired private AppConfigService configService;

    @BackendPermission(slug = BPermissionConstant.SYSTEM_CONFIG)
    @GetMapping("")
    @Log(title = "系统配置-读取", businessType = BusinessTypeConstant.GET)
    public JsonResponse index() {
        List<AppConfig> configs = configService.allShow();
        List<AppConfig> data = new ArrayList<>();
        for (AppConfig item : configs) {
            if (item.getIsPrivate() == 1 && StringUtil.isNotEmpty(item.getKeyValue())) {
                item.setKeyValue(SystemConstant.CONFIG_MASK);
            }
            data.add(item);
        }
        return JsonResponse.data(data);
    }

    @BackendPermission(slug = BPermissionConstant.SYSTEM_CONFIG)
    @PutMapping("")
    @Log(title = "系统配置-保存", businessType = BusinessTypeConstant.UPDATE)
    public JsonResponse save(@RequestBody AppConfigRequest req) {
        HashMap<String, String> data = new HashMap<>();
        req.getData()
                .forEach(
                        (key, value) -> {
                            // 过滤掉未变动的private配置
                            if (SystemConstant.CONFIG_MASK.equals(value)) {
                                return;
                            }
                            String saveValue = value;

                            // LDAP的url配置自动加ldap://处理
                            if (ConfigConstant.LDAP_URL.equals(key)
                                    && StringUtil.isNotEmpty(value)
                                    && !StringUtil.startsWithIgnoreCase(value, "ldap://")) {
                                saveValue = "ldap://" + saveValue;
                            }

                            data.put(key, saveValue);
                        });
        configService.saveFromMap(data);
        return JsonResponse.data(null);
    }
}

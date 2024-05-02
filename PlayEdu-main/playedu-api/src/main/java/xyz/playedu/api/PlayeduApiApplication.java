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
package xyz.playedu.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import xyz.playedu.common.config.UniqueNameGeneratorConfig;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableScheduling
@ComponentScan(
        basePackages = {"xyz.playedu"},
        nameGenerator = UniqueNameGeneratorConfig.class)
@MapperScan("xyz.playedu.**.mapper")
public class PlayeduApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayeduApiApplication.class, args);
    }
}

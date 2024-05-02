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
package xyz.playedu.api.controller;

import com.amazonaws.services.s3.model.AmazonS3Exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import xyz.playedu.common.exception.LimitException;
import xyz.playedu.common.exception.NotFoundException;
import xyz.playedu.common.exception.ServiceException;
import xyz.playedu.common.types.JsonResponse;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public JsonResponse exceptionHandler(Exception e) {
        log.error("出现异常", e);
        return JsonResponse.error("系统错误", 500);
    }

    @ExceptionHandler(ServiceException.class)
    public JsonResponse serviceExceptionHandler(ServiceException e) {
        return JsonResponse.error(e.getMessage(), 1);
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public JsonResponse serviceExceptionHandler(RedisConnectionFailureException e) {
        return JsonResponse.error("redis服务连接失败", 500);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResponse serviceExceptionHandler(HttpMessageNotReadableException e) {
        log.error("error", e);
        return JsonResponse.error("前端提交参数解析失败", 406);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResponse serviceExceptionHandler(MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError tmpError : allErrors) {
            errorMsg.append(tmpError.getDefaultMessage()).append(",");
        }
        String msg = errorMsg.substring(0, errorMsg.length() - 1);
        return JsonResponse.error(msg, 406);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResponse serviceExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return JsonResponse.error("请求method错误", 400);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public JsonResponse serviceExceptionHandler(MethodArgumentTypeMismatchException e) {
        return JsonResponse.error("请求错误", 400);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResponse serviceExceptionHandler(MissingServletRequestParameterException e) {
        return JsonResponse.error("参数错误", 406);
    }

    @ExceptionHandler(NotFoundException.class)
    public JsonResponse serviceExceptionHandler(NotFoundException e) {
        return JsonResponse.error(e.getMessage(), 404);
    }

    @ExceptionHandler(LimitException.class)
    public JsonResponse serviceExceptionHandler(LimitException e) {
        return JsonResponse.error("请稍后再试", 429);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public JsonResponse serviceExceptionHandler(AmazonS3Exception e) {
        log.error("s3错误={}", e.getMessage());
        return JsonResponse.error("存储配置有问题或存储无法无法正常访问", 500);
    }
}

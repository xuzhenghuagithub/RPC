package com.gupaoedu.rpc;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将要发布的接口添加此注解
 * 利用spring框架自动发布
 */
@Target(ElementType.TYPE)//使用方位，ElementType.TYPE用于类，接口，枚举
@Retention(RetentionPolicy.RUNTIME)//保留期，RetentionPolicy.RUNTIME运行时保留
@Component
public @interface GPRemoteService {
}

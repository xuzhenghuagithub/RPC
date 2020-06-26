package com.gupaoedu.rpc;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)//作用于字段
@Retention(RetentionPolicy.RUNTIME)//存在于运行时
@Component
public @interface MyReference {
}

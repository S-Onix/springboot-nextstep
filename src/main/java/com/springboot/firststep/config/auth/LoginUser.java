package com.springboot.firststep.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)      // 어노테이션이 적용될 위치 (여기서는 매개변수)
@Retention(RetentionPolicy.RUNTIME) // 이 어노테이션이 어떤 시점까지 영향을 미치는 결정(런타임까지)
public @interface LoginUser {       // LoginUser라는 어노테이션을 생성함
}

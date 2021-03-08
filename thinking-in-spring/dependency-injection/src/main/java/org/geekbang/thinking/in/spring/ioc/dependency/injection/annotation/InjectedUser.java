package org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入注解
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
package org.geekbang.thinking.in.spring.bean.definition.reginstbean;

import org.geekbang.thinking.in.spring.bean.definition.AnnotationBeanDefinitionDemo;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Java 注解配置元信息 3: @Import
 * @Author:JQK
 * @Date:2021/3/6 16:19
 **/

@Import(AnnotationByImport.Config.class)
public class AnnotationByImport {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(AnnotationByImport.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 按照类型依赖查找
        System.out.println("Config 类型的所有 Beans" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 Beans" + applicationContext.getBeansOfType(User.class));
        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    public static class Config {
        /**
         * 通过 Java 注解的方式，定义了一个 Bean
         */
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("小马哥");
            return user;
        }
    }
}

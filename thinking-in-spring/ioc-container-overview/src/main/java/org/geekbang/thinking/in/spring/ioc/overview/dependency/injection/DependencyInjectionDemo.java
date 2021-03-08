/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文,会有两个上下文存在
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection" +
                "-context.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println(userRepository);

        //userRepository对象里的BeanFactory和当前beanFactory不是同一个对象
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        //userRepository对象里的BeanFactory是一个内联对象
        System.out.println(userRepository.getBeanFactory());
        //说明依赖查找和依赖注入的依赖来源并不相同,这里代码会报错
       // System.out.println(beanFactory.getBean(BeanFactory.class));

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        //userRepository.getObjectFactory().getObject()和beanFactory是同一个对象，ApplicationContext是BeanFactory的子类，但毕竟还是两个对象
        System.out.println(objectFactory.getObject()==beanFactory);

        //userRepository.getObjectFactory().getObject() == applicationContext != userRepository.getBeanFactory()
        //ObjectFactory<ApplicationContext> 和 ObjectFactory<BeanFactory> 分别获取的 ApplicationContext 和 ApplicationContext 所关联的 BeanFactory，所以不会相等

        Environment environment=beanFactory.getBean(Environment.class);
        System.out.println(environment);
        //依赖的来源
        //userRepository:自定义的bean
        //beanFactory：spring内部依赖
        //environment:spring初始化的内建bean

        //内建依赖和自定义Bean的区别
        //1.内建依赖指的是DefaultListableBeanFactory属性resolvableDependencies这个map里面保存的bean，自定义bean
        // 指的是通过DefaultSingletonBeanRegistry#registerSingleton手动注册的bean。它们都在BeanFactory里面；
        //2.依赖注入的时候比如@AutoWired(AutowiredAnnotationBeanPostProcessor处理)
        // 会调用DefaultListableBeanFactory#resolveDependency方法去resolvableDependencies里面找，而依赖查找BeanFactory.getBean(xxx)
        // 是不会去resolvableDependencies这个map里面找的。

    }


}

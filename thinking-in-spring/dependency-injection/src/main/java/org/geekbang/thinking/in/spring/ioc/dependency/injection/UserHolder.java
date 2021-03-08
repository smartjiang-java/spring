package org.geekbang.thinking.in.spring.ioc.dependency.injection;

/**
 * @Author:JQK
 * @Date:2021/3/7 21:25
 **/


import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User} 的 Holder 类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class UserHolder {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
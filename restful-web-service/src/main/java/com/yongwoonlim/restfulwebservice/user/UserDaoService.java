package com.yongwoonlim.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {
    private static final List<User> users = new ArrayList<>();
    private static int sequence = 0;

    static {
        users.add(new User(++sequence, "Yongwoon", new Date()));
        users.add(new User(++sequence, "Yonghyun", new Date()));
        users.add(new User(++sequence, "Dion", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++sequence);
            user.setJoinDate(new Date());
        }

        users.add(user);
        return user;
    }
}

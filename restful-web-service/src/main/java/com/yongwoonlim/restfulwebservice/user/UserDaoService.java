package com.yongwoonlim.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserDaoService {
    private static final List<User> users = new ArrayList<>();
    private static int sequence = 0;

    static {
        users.add(new User(++sequence, "Yongwoon", new Date(), "pass1", "701010-1111111"));
        users.add(new User(++sequence, "Yonghyun", new Date(), "pass2", "701010-1111111"));
        users.add(new User(++sequence, "Dion", new Date(), "pass3", "701010-1111111"));
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findOne(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++sequence);
            user.setJoinDate(new Date());

            users.add(user);
        } else {
            Optional<User> userOptional = findOne(user.getId());

            if (userOptional.isEmpty()) {
                return null;
            }

            userOptional.get().setName(user.getName());
        }

        return user;
    }

    public Optional<User> deleteById(int id) {
        Optional<User> userOptional = findOne(id);
        userOptional.ifPresent(users::remove);
        return userOptional;
    }
}

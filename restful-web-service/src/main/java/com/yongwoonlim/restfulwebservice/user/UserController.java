package com.yongwoonlim.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDaoService userDaoService;

    @GetMapping
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable int id) {
        return userDaoService.findOne(id).orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found", id)));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}

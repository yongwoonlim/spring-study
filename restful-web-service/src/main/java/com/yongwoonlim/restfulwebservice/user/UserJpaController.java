package com.yongwoonlim.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jpa/users")
public class UserJpaController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User retrieveUserById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("ID[%d] not found", id)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri()).build();
    }

    @GetMapping("/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%d] not found", id)))
                .getPosts();
    }
}

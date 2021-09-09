package com.yongwoonlim.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    private final UserDaoService userDaoService;

    @GetMapping
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = userDaoService.findAll();

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable int id) {

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDaoService.findOne(id).orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found", id))));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        User updatedUser = userDaoService.save(user);

        if (updatedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", user.getId()));
        }

        return ResponseEntity.ok(user);
    }
}

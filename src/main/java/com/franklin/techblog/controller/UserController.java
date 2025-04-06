package com.franklin.techblog.controller;

import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<BlogUser>> getAllUsers() {
        System.out.println("@@@@@ CALLED @@@@");
        List<BlogUser> blogUsers = userService.getAllUsers();
        if (blogUsers.isEmpty()) {
            System.out.println("No Users in DB");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogUsers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogUser> getUserById(@PathVariable Long id) {
        Optional<BlogUser> user = userService.getUserById(id);
        System.out.println(user.isEmpty());
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @PostMapping
    // public ResponseEntity<User> createUser(@RequestBody User user) {
    // User newUser = userService.createUser(user);
    // return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    // }

    @PutMapping("{id}")
    public ResponseEntity<BlogUser> updateUser(@PathVariable Long id, @RequestBody BlogUser blogUser) {
        BlogUser updatedBlogUser = userService.updateUser(id, blogUser);
        return new ResponseEntity<>(updatedBlogUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String responseMessage = userService.deleteUserById(id);
        if (responseMessage.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
        return ResponseEntity.ok(responseMessage);
    }
}

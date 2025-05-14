package com.franklin.techblog.controller;

import com.franklin.techblog.constants.UserConstants;
import com.franklin.techblog.dto.*;
import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.service.IUserService;
import com.franklin.techblog.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final IUserService iUserService;
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody UserDto userDto) {
        iUserService.createAccount(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        boolean isAuthenticated = iUserService.authenticateUser(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, "Login successful! Redirecting to main page."));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDto(UserConstants.STATUS_401, "Invalid credentials!"));
        }
    }

    // TODO: return DTO rather than the BlogUser object
    @GetMapping
    public ResponseEntity<List<BlogUser>> getAllUsers() {
        List<BlogUser> users = userService.getAllUsers();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    // TODO: return DTO rather than the BlogUser object
    @GetMapping("/{id}")
    public ResponseEntity<BlogUser> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // TODO: return DTO rather than the BlogUser object
    @GetMapping("/get-by-email")
    public ResponseEntity<BlogUser> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // TODO: return DTO rather than the BlogUser object
    @PutMapping("/{id}")
    public ResponseEntity<BlogUser> updateUser(@PathVariable Long id, @RequestBody BlogUser blogUser) {
        BlogUser updated = userService.updateUser(id, blogUser);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String message = userService.deleteUserById(id);
        HttpStatus status = message.contains("not found") ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(message);
    }
}

package com.franklin.techblog.controller;

import com.franklin.techblog.Constants.UserConstants;
import com.franklin.techblog.service.IUserService;
import com.franklin.techblog.dto.LoginRequestDto;
import com.franklin.techblog.dto.ResponseDto;
import com.franklin.techblog.dto.UserDto;
import com.franklin.techblog.Exception.UserAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // Solves CORS issue
public class BlogController {

    private IUserService iUserService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@Valid @RequestBody UserDto userDto) {
        try {
            iUserService.createAccount(userDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(UserConstants.STATUS_401, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("500", "An unexpected error occurred. Please try again later."));
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto(UserConstants.STATUS_401, "Validation failed: " + errors));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        boolean isAuthenticated = iUserService.authenticateUser(loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword());

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
}

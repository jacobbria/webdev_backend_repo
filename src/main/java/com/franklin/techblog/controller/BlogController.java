package com.franklin.techblog.controller;


import com.franklin.techblog.Constants.UserConstants;
import com.franklin.techblog.Service.IUserService;
import com.franklin.techblog.dto.ResponseDto;
import com.franklin.techblog.dto.UserDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor

public class BlogController {

    private IUserService iUserService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody UserDto userDto) {
        iUserService.createAccount(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }







}

package com.exampleuserApi.controller;

import com.exampleuserApi.entities.User;
import com.exampleuserApi.exceptions.InvalidRequestBodyException;
import com.exampleuserApi.pojo.ApiResponse;
import com.exampleuserApi.sevices.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService usersService) {
        this.userService = usersService;
    }

    @ApiOperation( "find all users" )
    @GetMapping
    @CrossOrigin
    public ResponseEntity findAllUsers(){
        return ResponseEntity.status(200).body(userService.findAllUsers());
    }

    @ApiOperation( "add users" )
    @PostMapping
    @CrossOrigin
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors())
            throw new InvalidRequestBodyException( getValidationErrors.apply(errors) );
        return ResponseEntity.status(201)

                .contentType(MediaType.APPLICATION_JSON_UTF8)

                .body(userService.addUser(user));
    }

    @ApiOperation( "update user by id")
    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody @Valid User users, Errors errors){
        if (errors.hasErrors())
            throw new InvalidRequestBodyException( getValidationErrors.apply(errors) );
        return ResponseEntity.status(200).body(userService.updateUser(id, users));
    }

    @ApiOperation( "delete user by id")
    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity deleteUser(@PathVariable Long id){
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(ApiResponse.builder().code(200)
                    .status("OK").message("data at id "+id+" successfully deleted").build());
        return ResponseEntity.status(400).body(ApiResponse.builder().code(400).
                status("BAD REQUEST").message("something went wrong").build());
    }

    private Function<Errors,String> getValidationErrors = err ->
            err.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
}
package com.aram.user.controllers;

import com.aram.user.model.User;
import com.aram.user.services.UserService;
import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping(value="v1/users")
@Api(tags = "User API")
@Slf4j
public class UserController {
    
    @Autowired
    private UserService userService;


    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
        Optional<User> user = this.userService.getUser(userId);
        if( !user.isPresent() ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        } 
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("userId") String orgId, @RequestBody User user) {
        User userUpdated = this.userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        User userSaved = this.userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(userSaved);
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") String userId,  @RequestBody User user) {
        this.userService.deleteUser(user);
    }
}

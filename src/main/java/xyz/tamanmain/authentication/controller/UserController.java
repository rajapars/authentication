package xyz.tamanmain.authentication.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import xyz.tamanmain.authentication.model.User;
import xyz.tamanmain.authentication.model.request.SignInRequest;
import xyz.tamanmain.authentication.model.response.SignInResponse;
import xyz.tamanmain.authentication.service.UserService;
import xyz.tamanmain.authentication.util.JwtTokenUtil;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/verify")
    @ApiOperation(value = "Verify user token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer access_token", required = true, dataType = "string", paramType = "header")
    })
    public void verifyUserToken() {}

    @PostMapping(value = "/register", produces = "application/json")
    @ApiOperation(value = "Register new user")
    public ResponseEntity<User> register(@RequestBody User user) {
        User newUser = userService.register(user);
        if (newUser != null) {
            newUser.setPassword("");
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = "/signin", produces = "application/json")
    @ApiOperation(value = "Sign In using username and password")
    public ResponseEntity<SignInResponse> signin(@RequestBody(required = true) SignInRequest user) throws Exception {
        authenticate(user);

        User currentUser = userService.findByUsername(user.getUsername());

        if (currentUser == null) {
            throw new UsernameNotFoundException("User with username '" + user.getUsername() + "' not found!");
        }

        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new SignInResponse(token));
    }

    private void authenticate(SignInRequest user) throws Exception {
        UsernamePasswordAuthenticationToken userPassToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        try {
            authenticationManager.authenticate(userPassToken);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIAL", e);
        }
    }

}

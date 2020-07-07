package cl.transbank.restaurant_api.controller;

import cl.transbank.restaurant_api.entity.Credentials;
import cl.transbank.restaurant_api.entity.User;
import cl.transbank.restaurant_api.service.TokenAuthenticationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    TokenAuthenticationService tokenAuthenticationService;

    private static final Gson gson = new Gson();

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody User user) {

        if (user.getPassword().equals("123")){
            String token = tokenAuthenticationService.getJWTToken(user.getUsername());
            Credentials credentials = new Credentials(user.getUsername(), token);

            return new ResponseEntity<>(gson.toJson(credentials), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Credentials error"), HttpStatus.UNAUTHORIZED);
    }
}

package cl.transbank.restaurant_api.controller;

import cl.transbank.restaurant_api.entity.User;
import cl.transbank.restaurant_api.service.TokenAuthenticationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    private static final Gson gson = new Gson();

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        if (pwd.equals("123")){
            String token = tokenAuthenticationService.getJWTToken(username);
            User user = new User();
            user.setUsername(username);
            user.setToken(token);

            return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Credentials error"), HttpStatus.UNAUTHORIZED);
    }
}

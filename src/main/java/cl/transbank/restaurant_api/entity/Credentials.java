package cl.transbank.restaurant_api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Credentials {

    private String username;
    private String token;

    public Credentials(String username, String token) {
        this.username = username;
        this.token = token;
    }
}

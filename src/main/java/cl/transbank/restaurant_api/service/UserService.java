package cl.transbank.restaurant_api.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

@Service
public class UserService {

    private static final String MY_SECRET_KEY = "mysecretkey";

    private String getPassword() throws IOException {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        Properties props = new EncryptableProperties(encryptor);

        encryptor.setPassword(MY_SECRET_KEY);
        props.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));

        return props.getProperty("password");
    }

    private String getUser() throws IOException {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        Properties props = new EncryptableProperties(encryptor);

        encryptor.setPassword(MY_SECRET_KEY);
        props.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        return props.getProperty("user");
    }

    public boolean authenticate(String username, String password) throws IOException {
        return (this.getPassword().equals(password) && this.getUser().equals(username));
    }
}

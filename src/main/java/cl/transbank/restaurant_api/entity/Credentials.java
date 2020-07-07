package cl.transbank.restaurant_api.entity;

public class Credentials {

    private String usename;
    private String token;

    public Credentials(String usename, String token) {
        this.usename = usename;
        this.token = token;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

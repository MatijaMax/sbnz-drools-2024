package sbnz.dto;

public class UserLoginDTO {
    private String email;
    private String password;

    public UserLoginDTO() {}

    public UserLoginDTO(String username, String password) {
        this.email = username;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

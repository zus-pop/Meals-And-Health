package dto;

import java.io.Serializable;
import utils.Role;

/**
 *
 * @author hoang
 */
public class UserAccount implements Serializable {

    private int id;
    private String userName;
    private String passWord;
    private String phone;
    private String email;
    private boolean active;
    private Role role;

    public UserAccount() {
    }

    public UserAccount(
            int id,
            String userName,
            String passWord,
            String phone,
            String email,
            boolean active,
            Role role) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.email = email;
        this.active = active;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserAccount{" + "id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", phoneNumber=" + phone + ", email=" + email + ", active=" + active + ", role=" + role + '}';
    }
    
    
}

package cn.tedu.boot011.entity;

/**
 * @author yaohao
 */
public class User {
    private String username;
    private String nickname;
    private String password;

    public User(){}
    public User(String name, String nickname, String password) {
        this.username = name;
        this.nickname = nickname;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

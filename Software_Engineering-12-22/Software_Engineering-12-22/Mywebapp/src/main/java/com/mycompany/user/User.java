package com.mycompany.user;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "users_db2")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, name = "userName")
    private String userName;
    @Column(length = 45, nullable = false, name = "accountNumber")
    private String accountNumber;
    @Column(length = 45, nullable = false, name = "password")
    private String password;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public String getAccountNumber() { return accountNumber; }
    public String getPassword() {
        return password;
    }
    public void setUserName(String userName) { this.userName = userName; }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +

                ", userName='" + getUserName() + '\'' +
                '}' +

                ", accountNumber='" + getAccountNumber() + '\'' +
                '}' +

                ", password='" + getPassword() + '\'' +
                '}';
    }

}

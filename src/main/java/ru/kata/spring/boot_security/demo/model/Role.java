package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    int role_id;

    @Column
    String name;

    @ManyToMany(mappedBy = "roles")
    List<User> userList = new ArrayList<>();

    public Role(){}

    public Role(int role_id, String name, List<User> userList) {
        this.role_id = role_id;
        this.userList = userList;
        this.name = name;

    }

    @Override
    public String getAuthority() {
        return getName();
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name of role: " + name;
    }
}
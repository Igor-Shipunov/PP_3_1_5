package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    private int role_id;

    @Column
    private String name;

    public Role(){}

    public Role(String name) {
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
        //return name.substring(5); // <- как должно быть
    }
}

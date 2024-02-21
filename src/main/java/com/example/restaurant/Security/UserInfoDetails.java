package com.example.restaurant.Security;


import com.example.restaurant.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserInfoDetails implements UserDetails {
    private String email;
    private String password;
    private List<GrantedAuthority> roles;
    public String getRole() {
        return this.roles.toString();
    }
    public UserInfoDetails(User employee){
        this.email = employee.getEmail();
        this.password = employee.getPassword();
        this.roles = List.of(new SimpleGrantedAuthority(employee.getRole()));
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

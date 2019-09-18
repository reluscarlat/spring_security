package com.resc.springsecuritydemo101.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.List;

public class AppUserDetailsManager implements UserDetailsManager {

    List<UserDetails> users = new ArrayList<>();

    @Override
    public void createUser(UserDetails userDetails) {
        users.add(userDetails);
    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String username) {
        for(UserDetails u : this.users) {
            if(u.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for(UserDetails u : this.users) {
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        throw new UsernameNotFoundException("User not found.");
    }
}

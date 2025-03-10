package com.codesoom.assignment.security;

import com.codesoom.assignment.domain.Role;
import java.util.stream.Collectors;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class UserAuthentication extends AbstractAuthenticationToken {

  private final Long userId;

  public UserAuthentication(Long userId, List<Role> roles) {
    super(authorities(roles));
    this.userId = userId;
  }


  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public String toString() {
    return String.format("UserAuthentication{userId=%d}", userId);
  }

  @Override
  public boolean isAuthenticated() {
    return true;
  }

  public Long getUserId() {
    return userId;
  }

  @Override
  public Object getPrincipal() {
    return userId;
  }

  private static List<GrantedAuthority> authorities( List<Role> roles) {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

  }

}
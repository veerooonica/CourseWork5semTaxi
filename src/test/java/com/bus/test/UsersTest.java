package com.bus.test;

import com.bus.models.Users;
import com.bus.models.enums.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {

    private Users user;

    @BeforeEach
    public void setup() {
        user = new Users("testUser", "password123", "AB123456", "1234567890", Roles.Клиент);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("testUser", user.getUsername());
        assertEquals("AB123456", user.getPassport());
        assertEquals("1234567890", user.getTel());
        assertEquals(Roles.Клиент, user.getRole());

        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());

        user.setPassport("CD789012");
        assertEquals("CD789012", user.getPassport());

        user.setTel("0987654321");
        assertEquals("0987654321", user.getTel());

        user.setRole(Roles.Водитель);
        assertEquals(Roles.Водитель, user.getRole());
    }

    @Test
    public void testPasswordEncoder() {
        assertTrue(user.getPassword().startsWith("$2a$12$"));

        String rawPassword = "password123";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        assertTrue(passwordEncoder.matches(rawPassword, user.getPassword()));

        String invalidPassword = "wrongPassword";
        assertFalse(passwordEncoder.matches(invalidPassword, user.getPassword()));
    }

    @Test
    public void testUserDetailsMethods() {
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(Roles.Клиент));
    }
}

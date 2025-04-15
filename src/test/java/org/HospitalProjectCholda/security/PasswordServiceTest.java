package org.HospitalProjectCholda.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordServiceTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testForHashedPassword() {
        String originalPassword = "password";
        String hashedPassword = passwordEncoder.encode(originalPassword);

        assertNotEquals(originalPassword, hashedPassword);
        System.out.println(hashedPassword);
        System.out.println(originalPassword);

    }

}

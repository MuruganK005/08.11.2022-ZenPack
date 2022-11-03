package com.ZenPack.interceptor;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = {JwtTokenUtil.class})
@ExtendWith(SpringExtension.class)
class JwtTokenUtilTest {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    @Disabled("TODO: Complete this test")
    void testGetUsernameFromToken() {
        jwtTokenUtil.getUsernameFromToken("ABC123");
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUsernameFromToken2() {
        jwtTokenUtil.getUsernameFromToken("com.ZenPack.interceptor.JwtTokenUtil");
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUsernameFromToken3() {
        jwtTokenUtil.getUsernameFromToken("");
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testGetExpirationDateFromToken() {
        jwtTokenUtil.getExpirationDateFromToken("ABC123");
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testGetExpirationDateFromToken2() {
        jwtTokenUtil.getExpirationDateFromToken("com.ZenPack.interceptor.JwtTokenUtil");
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testGetExpirationDateFromToken3() {
        jwtTokenUtil.getExpirationDateFromToken("");
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testGetClaimFromToken() {
        String token = "";
        Function<Claims, Object> claimsResolver = null;

        // Act
        Object actualClaimFromToken = this.jwtTokenUtil.getClaimFromToken(token, claimsResolver);

    }
    @Test
    void testValidateToken() {
        assertFalse(jwtTokenUtil.validateToken("ABC123"));
        assertFalse(jwtTokenUtil.validateToken(JwtTokenUtil.TOKEN_PREFIX));
        assertFalse(jwtTokenUtil.validateToken("com.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("ABC123Bearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer ABC123"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Bearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer zenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token Invalid {}"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token"));
        assertFalse(jwtTokenUtil.validateToken("Bearer com.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("Bearer 42"));
        assertFalse(jwtTokenUtil.validateToken("zenfra_migrationBearer "));
        assertFalse(jwtTokenUtil.validateToken("Token Invalid {}Bearer "));
        assertFalse(jwtTokenUtil.validateToken("TokenBearer "));
        assertFalse(jwtTokenUtil.validateToken("com.ZenPack.interceptor.JwtTokenUtilBearer "));
        assertFalse(jwtTokenUtil.validateToken("42Bearer "));
        assertFalse(jwtTokenUtil.validateToken("ABC123ABC123Bearer "));
        assertFalse(jwtTokenUtil.validateToken("ABC123Bearer ABC123"));
        assertFalse(jwtTokenUtil.validateToken("ABC123Bearer Bearer "));
        assertFalse(jwtTokenUtil.validateToken("ABC123Bearer zenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("ABC123Bearer Token Invalid {}"));
        assertFalse(jwtTokenUtil.validateToken("ABC123Bearer Token"));
        assertFalse(jwtTokenUtil.validateToken("ABC123Bearer com.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("ABC123Bearer 42"));
        assertFalse(jwtTokenUtil.validateToken("ABC123zenfra_migrationBearer "));
        assertFalse(jwtTokenUtil.validateToken("ABC123Token Invalid {}Bearer "));
        assertFalse(jwtTokenUtil.validateToken("ABC123TokenBearer "));
        assertFalse(jwtTokenUtil.validateToken("ABC123com.ZenPack.interceptor.JwtTokenUtilBearer "));
        assertFalse(jwtTokenUtil.validateToken("ABC12342Bearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer ABC123ABC123"));
        assertFalse(jwtTokenUtil.validateToken("Bearer ABC123Bearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer ABC123zenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("Bearer ABC123Token Invalid {}"));
        assertFalse(jwtTokenUtil.validateToken("Bearer ABC123Token"));
        assertFalse(jwtTokenUtil.validateToken("Bearer ABC123com.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("Bearer ABC12342"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Bearer ABC123"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Bearer Bearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer Bearer zenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Bearer Token Invalid {}"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Bearer Token"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Bearer com.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Bearer 42"));
        assertFalse(jwtTokenUtil.validateToken("Bearer zenfra_migrationABC123"));
        assertFalse(jwtTokenUtil.validateToken("Bearer zenfra_migrationBearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer zenfra_migrationzenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("Bearer zenfra_migrationToken Invalid {}"));
        assertFalse(jwtTokenUtil.validateToken("Bearer zenfra_migrationToken"));
        assertFalse(jwtTokenUtil.validateToken("Bearer zenfra_migrationcom.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("Bearer zenfra_migration42"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token Invalid {}ABC123"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token Invalid {}Bearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token Invalid {}zenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token Invalid {}Token Invalid {}"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token Invalid {}Token"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token Invalid {}com.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token Invalid {}42"));
        assertFalse(jwtTokenUtil.validateToken("Bearer TokenABC123"));
        assertFalse(jwtTokenUtil.validateToken("Bearer TokenBearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer Tokenzenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("Bearer TokenToken Invalid {}"));
        assertFalse(jwtTokenUtil.validateToken("Bearer TokenToken"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Tokencom.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("Bearer Token42"));
        assertFalse(jwtTokenUtil.validateToken("Bearer com.ZenPack.interceptor.JwtTokenUtilABC123"));
        assertFalse(jwtTokenUtil.validateToken("Bearer com.ZenPack.interceptor.JwtTokenUtilBearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer com.ZenPack.interceptor.JwtTokenUtilzenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("Bearer com.ZenPack.interceptor.JwtTokenUtilToken Invalid {}"));
        assertFalse(jwtTokenUtil.validateToken("Bearer com.ZenPack.interceptor.JwtTokenUtilToken"));
        assertFalse(jwtTokenUtil
                .validateToken("Bearer com.ZenPack.interceptor.JwtTokenUtilcom.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("Bearer com.ZenPack.interceptor.JwtTokenUtil42"));
        assertFalse(jwtTokenUtil.validateToken("Bearer 42ABC123"));
        assertFalse(jwtTokenUtil.validateToken("Bearer 42Bearer "));
        assertFalse(jwtTokenUtil.validateToken("Bearer 42zenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("Bearer 42Token Invalid {}"));
        assertFalse(jwtTokenUtil.validateToken("Bearer 42Token"));
        assertFalse(jwtTokenUtil.validateToken("Bearer 42com.ZenPack.interceptor.JwtTokenUtil"));
        assertFalse(jwtTokenUtil.validateToken("Bearer 4242"));
        assertFalse(jwtTokenUtil.validateToken("zenfra_migrationABC123Bearer "));
        assertFalse(jwtTokenUtil.validateToken("zenfra_migrationBearer ABC123"));
        assertFalse(jwtTokenUtil.validateToken("zenfra_migrationBearer Bearer "));
        assertFalse(jwtTokenUtil.validateToken("zenfra_migrationBearer zenfra_migration"));
        assertFalse(jwtTokenUtil.validateToken("zenfra_migrationBearer Token Invalid {}"));
    }
}


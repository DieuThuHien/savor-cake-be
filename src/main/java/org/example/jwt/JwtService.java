package org.example.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.entity.Account;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
    public String generateToken(Account account){
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        Map<String, String> claims = new HashMap<>();
        claims.put("username", account.getUsername());
//        LocalDateTime expiredDateTime = now.plusMinutes(time);
        return Jwts.builder()
                .setSubject(account.getUsername())
                .setIssuedAt(Date.from(now.atZone(ZoneOffset.UTC).toInstant()))
                .setClaims(claims)
//                .setExpiration(Date.from(expiredDateTime.atZone(ZoneOffset.UTC).toInstant()))
                .signWith(SignatureAlgorithm.HS512, "9f1eA7ddMGCgI+ApU8vxmScHA7zZG3HE/qhYF2aLIFl4gacRfcfkMO/P5QAW4By0fnPns6dE+MzIa1XE+xhN3BHaaRy6Jmn22KY/unkiojGeBvYy0zFgCcD+kqSOXGQnYNo6/b0HyWk=").compact();
    }
}

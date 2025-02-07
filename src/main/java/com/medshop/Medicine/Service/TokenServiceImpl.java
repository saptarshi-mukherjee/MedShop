package com.medshop.Medicine.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("jwt")
public class TokenServiceImpl implements TokenService {

    private static final Long access_token_expiry=600000L;
    private static final Long refresh_token_expiry=1800000L;

    @Override
    public String getAccessToken(String username, List<String> roles) {
        HashMap<String,Object> claims=new HashMap<>();
        claims.put("roles", roles);
        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuer("MEDSHOP")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+access_token_expiry))
                .and()
                .signWith(generateKey())
                .compact();
    }

    @Override
    public String getRefreshToken(String username, List<String> roles) {
        HashMap<String,Object> claims=new HashMap<>();
        claims.put("roles",roles);
        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuer("MEDSHOP")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+refresh_token_expiry))
                .and()
                .signWith(generateKey())
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public boolean isValidToken(String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    @Override
    public List<String> extractRoles(String token) {
        List<Object> roles_obj=getClaims(token).get("roles", List.class);
        List<String> roles=new ArrayList<>();
        for(Object obj : roles_obj) {
            if(obj instanceof String)
                roles.add((String)obj);
        }
        return roles;
    }

    private SecretKey generateKey() {
        byte[] decode= Decoders.BASE64.decode(getKey());
        return Keys.hmacShaKeyFor(decode);
    }


    private String getKey() {
        String key="7uyfriImbYeXUQe8mvLs0tjSfpUpX8AgLzkIXMguyhs=";
        return key;
    }

    private Claims getClaims(String token) {
        Claims claims=null;
        try {
            claims=Jwts
                    .parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }
        catch(ExpiredJwtException e) {
            claims=e.getClaims();
        }
        return claims;
    }

    private Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }
}

package com.medshop.Medicine.Service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
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

    private SecretKey generateKey() {
        byte[] decode= Decoders.BASE64.decode(getKey());
        return Keys.hmacShaKeyFor(decode);
    }


    private String getKey() {
        String key="7uyfriImbYeXUQe8mvLs0tjSfpUpX8AgLzkIXMguyhs=";
        return key;
    }
}

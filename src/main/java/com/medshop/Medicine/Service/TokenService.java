package com.medshop.Medicine.Service;

import java.util.List;

public interface TokenService {
    public String getAccessToken(String username, List<String> roles);
    public String getRefreshToken(String username, List<String> roles);
    public String extractUsername(String token);
    public boolean isValidToken(String token);
    public List<String> extractRoles(String token);
}

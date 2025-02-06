package com.medshop.Medicine.Service;

import com.medshop.Medicine.Models.RefreshToken;

public interface RefreshTokenService {
    public void saveRefreshToken(RefreshToken token);
    public RefreshToken getRefreshToken(String username);
    public void deleteRefreshToken(RefreshToken token);
}

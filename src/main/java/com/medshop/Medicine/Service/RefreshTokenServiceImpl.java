package com.medshop.Medicine.Service;


import com.medshop.Medicine.Models.RefreshToken;
import com.medshop.Medicine.Repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    RefreshTokenRepository token_repo;

    @Override
    public void saveRefreshToken(RefreshToken token) {
        token_repo.save(token);
    }

    @Override
    public RefreshToken getRefreshToken(String username) {
        return token_repo.fetchByUsername(username);
    }

    @Override
    public void deleteRefreshToken(RefreshToken token) {
        token_repo.delete(token);
    }
}

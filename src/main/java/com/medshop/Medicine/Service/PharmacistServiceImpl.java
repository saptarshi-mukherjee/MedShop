package com.medshop.Medicine.Service;


import com.medshop.Medicine.Models.Pharmacist;
import com.medshop.Medicine.Observer.UserRegistrationPublisher;
import com.medshop.Medicine.Repositories.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    UserRegistrationPublisher publisher;
    @Autowired
    PharmacistRepository pharma_repo;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public List<Pharmacist> registerPharmacist(String username, String password, String full_name, String role, String college) {
        Pharmacist pharmacist=Pharmacist
                .getBuilder()
                .setUsername(username)
                .setPassword(encoder.encode(password))
                .setFull_name(full_name)
                .setRole(role)
                .setCreated_at()
                .setCollege(college)
                .build();
        publisher.execute(pharmacist);
        return getAllPharmacists();
    }

    @Override
    public List<Pharmacist> getAllPharmacists() {
        return pharma_repo.fetchAllPharmacists();
    }
}

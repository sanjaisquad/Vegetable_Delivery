package com.greengroc.v1api.services;

import com.greengroc.v1api.models.SuperAdmin;
import com.greengroc.v1api.repositories.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminService {

    @Autowired
    private SuperAdminRepository superAdminRepository;

    public SuperAdmin registerSuperAdmin(SuperAdmin superAdmin) {
        return superAdminRepository.save(superAdmin);
    }
}

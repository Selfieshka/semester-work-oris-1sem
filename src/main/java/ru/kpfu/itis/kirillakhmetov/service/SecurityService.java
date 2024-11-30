package ru.kpfu.itis.kirillakhmetov.service;

import ru.kpfu.itis.kirillakhmetov.dao.OwnerDao;
import ru.kpfu.itis.kirillakhmetov.dto.SignUpOwnerDto;
import ru.kpfu.itis.kirillakhmetov.entity.Owner;
import ru.kpfu.itis.kirillakhmetov.util.PasswordHashingUtil;

import java.util.Optional;

public class SecurityService {
    private final OwnerDao ownerDao;

    public SecurityService(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public boolean signUp(SignUpOwnerDto signUpOwnerDto) {
        Optional<Owner> ownerFromDb = ownerDao.findByEmail(signUpOwnerDto.email());
        if (ownerFromDb.isEmpty()) {
            ownerDao.save(Owner.builder()
                    .firstName(signUpOwnerDto.firstName())
                    .email(signUpOwnerDto.email())
                    .password(PasswordHashingUtil.encrypt(signUpOwnerDto.password()))
                    .businessName(signUpOwnerDto.businessName())
                    .build());
            return true;
        } else {
            return false;
        }
    }
}

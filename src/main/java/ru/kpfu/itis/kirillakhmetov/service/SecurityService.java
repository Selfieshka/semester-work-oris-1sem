package ru.kpfu.itis.kirillakhmetov.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.kpfu.itis.kirillakhmetov.dao.OwnerDao;
import ru.kpfu.itis.kirillakhmetov.dto.SignInOwnerDto;
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

    public boolean signIn(SignInOwnerDto signInOwnerDto) {
        Optional<Owner> ownerFromDb = ownerDao.findByEmail(signInOwnerDto.email());
        if (ownerFromDb.isPresent()) {
            Owner owner = ownerFromDb.get();
            return owner.getEmail().equals(signInOwnerDto.email())
                    && PasswordHashingUtil.compare(signInOwnerDto.password(), owner.getPassword());
        }
        return false;
    }

    public boolean isSigned(HttpServletRequest req) {
        return req.getSession().getAttribute("email") != null;
    }

    public void signOut(HttpServletRequest req) {
        req.getSession().removeAttribute("email");
    }
}

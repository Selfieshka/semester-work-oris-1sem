package ru.kpfu.itis.kirillakhmetov.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.kpfu.itis.kirillakhmetov.dao.OwnerDao;
import ru.kpfu.itis.kirillakhmetov.dto.SignInOwnerDto;
import ru.kpfu.itis.kirillakhmetov.dto.SignUpOwnerDto;
import ru.kpfu.itis.kirillakhmetov.entity.Owner;
import ru.kpfu.itis.kirillakhmetov.exception.ValidationException;
import ru.kpfu.itis.kirillakhmetov.util.PasswordHashingUtil;
import ru.kpfu.itis.kirillakhmetov.validator.Error;
import ru.kpfu.itis.kirillakhmetov.validator.SignInOwnerValidator;
import ru.kpfu.itis.kirillakhmetov.validator.SignUpOwnerValidator;
import ru.kpfu.itis.kirillakhmetov.validator.ValidationResult;

import java.util.Optional;

public class SecurityService {
    private final OwnerDao ownerDao;
    private final SignUpOwnerValidator signUpOwnerValidator = SignUpOwnerValidator.getInstance();
    private final SignInOwnerValidator signInOwnerValidator = SignInOwnerValidator.getInstance();

    public SecurityService(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public void signUp(SignUpOwnerDto signUpOwnerDto) throws ValidationException {
        ValidationResult validationResult = signUpOwnerValidator.isValid(signUpOwnerDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        Optional<Owner> ownerFromDb = ownerDao.findByEmail(signUpOwnerDto.email());
        if (ownerFromDb.isEmpty()) {
            ownerDao.save(Owner.builder()
                    .firstName(signUpOwnerDto.firstName())
                    .email(signUpOwnerDto.email())
                    .password(PasswordHashingUtil.encrypt(signUpOwnerDto.password()))
                    .businessName(signUpOwnerDto.businessName())
                    .build());
        } else {
            validationResult.add(new Error("validation.error", "Такой пользователь уже существует"));
            throw new ValidationException(validationResult.getErrors());
        }
    }

    public void signIn(SignInOwnerDto signInOwnerDto) throws ValidationException {
        ValidationResult validationResult = signInOwnerValidator.isValid(signInOwnerDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        Optional<Owner> ownerFromDb = ownerDao.findByEmail(signInOwnerDto.email());
        if (ownerFromDb.isPresent()) {
            Owner owner = ownerFromDb.get();
            if (!PasswordHashingUtil.compare(signInOwnerDto.password(), owner.getPassword())) {
                validationResult.add(new Error("validation.password", "Неверный пароль"));
                throw new ValidationException(validationResult.getErrors());
            }
        } else {
            validationResult.add(new Error("validation.email", "Пользователь с таким email не найден"));
            throw new ValidationException(validationResult.getErrors());
        }
    }

    public boolean isSigned(HttpServletRequest req) {
        return req.getSession().getAttribute("owner") != null;
    }

    public void signOut(HttpServletRequest req) {
        req.getSession().removeAttribute("id");
        req.getSession().removeAttribute("owner");
    }
}

package ru.kpfu.itis.kirillakhmetov.service;

import jakarta.servlet.http.Part;
import ru.kpfu.itis.kirillakhmetov.dao.OwnerDao;
import ru.kpfu.itis.kirillakhmetov.dto.OwnerDto;
import ru.kpfu.itis.kirillakhmetov.entity.Owner;
import ru.kpfu.itis.kirillakhmetov.util.CloudinaryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class OwnerService {
    private final OwnerDao ownerDao;

    public OwnerService(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public Long getOwnerIdByEmail(String email) {
        return ownerDao.findByEmail(email).get().getId();
    }

    public Optional<OwnerDto> getProfileInfo(String email) {
        Optional<Owner> ownerFromDb = ownerDao.findByEmail(email);
        if (ownerFromDb.isPresent()) {
            Owner owner = ownerFromDb.get();
            return Optional.of(new OwnerDto(
                    owner.getFirstName(),
                    owner.getLastName(),
                    owner.getPatronymic(),
                    owner.getAge(),
                    owner.getEmail(),
                    owner.getPhoneNumber(),
                    owner.getProfilePhotoUrl()));
        }
        return Optional.empty();
    }

    public OwnerDto changePersonalData(OwnerDto ownerDto) {
        ownerDao.update(Owner.builder()
                .firstName(ownerDto.firstName())
                .lastName(ownerDto.lastName())
                .patronymic(ownerDto.patronymic())
                .age(ownerDto.age())
                .email(ownerDto.email())
                .phoneNumber(ownerDto.phoneNumber())
                .build());
        Owner updatedOwner = ownerDao.findByEmail(ownerDto.email()).get();
        return new OwnerDto(
                updatedOwner.getFirstName(),
                updatedOwner.getLastName(),
                updatedOwner.getPatronymic(),
                updatedOwner.getAge(),
                updatedOwner.getEmail(),
                updatedOwner.getPhoneNumber(),
                updatedOwner.getProfilePhotoUrl());
    }

    public void deleteOwner(String email) {
        ownerDao.deleteByEmail(email);
    }

    public String uploadProfilePhoto(Part photo, String email) {
        try (InputStream inputStream = photo.getInputStream()) {
            String url = CloudinaryUtil.uploadImage(inputStream, photo.getSubmittedFileName());
            ownerDao.updateProfilePhotoUrlByEmail(url, email);
            return url;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

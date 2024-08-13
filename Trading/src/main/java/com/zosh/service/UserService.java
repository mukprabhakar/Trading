package com.zosh.service;

import com.zosh.modal.User;
import com.zosh.domain.VerificationType;

public interface UserService {

    public User findUserProfileByJwt(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public User findUserById(Long userId) throws Exception;

    public User enableTwoFactorAuthentication(VerificationType verificationType,String sendTo, User user);

    User updatePassword(User user, String newPassword);
}

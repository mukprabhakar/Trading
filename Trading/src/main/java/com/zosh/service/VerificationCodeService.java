package com.zosh.service;

import com.zosh.modal.User;
import com.zosh.modal.VerificationCode;
import com.zosh.domain.VerificationType;

public interface VerificationCodeService {
    VerificationCode sendVerificationCode(User user, VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode getVerificationCodeByUser(Long userId);

    void deleteVerificationCodeById(VerificationCode verificationCode);

}

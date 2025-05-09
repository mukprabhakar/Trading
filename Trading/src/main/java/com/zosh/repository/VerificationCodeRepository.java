package com.zosh.repository;

import com.zosh.modal.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository  extends JpaRepository<VerificationCode,Long> {

    public VerificationCode findByUserId(Long userId);
}

package com.zosh.repository;

import com.zosh.modal.TwoFactorOTP;
import com.zosh.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOTP,String> {
//    TwoFactorOTP findByUser(User userId);

    TwoFactorOTP findByUserId(Long userId);
}

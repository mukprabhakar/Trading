package com.zosh.modal;

import com.zosh.domain.VerificationType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String otp;

    @OneToOne
    @JoinColumn(name = "user_id") // Ensure this matches the field in User
    private User user; // Correctly reference the User entity

    private String email;

    private String mobile;

    private VerificationType verificationType;
}

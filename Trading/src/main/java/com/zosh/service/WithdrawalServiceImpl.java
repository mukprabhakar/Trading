package com.zosh.service;

import com.zosh.domain.WithdrawalStatus;
import com.zosh.modal.User;
import com.zosh.modal.Withdrawal;
import com.zosh.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Override
    public Withdrawal requestyWithdrawal(Long amount, User user) {
        Withdrawal withdrawal=new Withdrawal();
        withdrawal.setAmount(amount);
        withdrawal.setUser(user);
        withdrawal.setStatus(WithdrawalStatus.PENDING);
        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Withdrawal procedWithWithdrawal(Long withdrawlId, boolean accept) throws Exception {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(withdrawlId);
        if (optionalWithdrawal.isEmpty()) {
            throw new Exception("Withdrawal not found");
        }
        Withdrawal withdrawal = optionalWithdrawal.get();
        withdrawal.setDate(LocalDateTime.now());
        if (accept) {
            withdrawal.setStatus(WithdrawalStatus.SUCCESS);
        } else {
            withdrawal.setStatus(WithdrawalStatus.REJECTED); // Use REJECTED status for non-acceptance
        }
        return withdrawalRepository.save(withdrawal);
    }

//    @Override
//    public Withdrawal proceedWithWithdrawal(Long withdrawalId, boolean accept) throws Exception {
//        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(withdrawalId);
//        if (optionalWithdrawal.isEmpty()) {
//            throw new Exception("Withdrawal not found");
//        }
//        Withdrawal withdrawal = optionalWithdrawal.get();
//        withdrawal.setDate(LocalDateTime.now());
//        if (accept) {
//            withdrawal.setStatus(WithdrawalStatus.SUCCESS);
//        } else {
//            withdrawal.setStatus(WithdrawalStatus.REJECTED); // Use REJECTED status for non-acceptance
//        }
//        return withdrawalRepository.save(withdrawal);
//    }

    @Override
    public List<Withdrawal> getUsersWithdrawalHistory(User user) {
        return withdrawalRepository.findByUserId(user.getId());
    }

    @Override
    public List<Withdrawal> getAllWithdrawalRequest() {
        return withdrawalRepository.findAll();
    }
}

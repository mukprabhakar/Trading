package com.zosh.service;

import com.zosh.modal.User;
import com.zosh.modal.Withdrawal;

import java.util.List;

public interface WithdrawalService {

    Withdrawal requestyWithdrawal(Long amount, User user);

    Withdrawal procedWithWithdrawal(Long withdrawlId,boolean accept) throws Exception;

    List<Withdrawal> getUsersWithdrawalHistory(User user);

    List<Withdrawal> getAllWithdrawalRequest();
}

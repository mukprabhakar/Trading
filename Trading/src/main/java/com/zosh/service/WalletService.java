package com.zosh.service;

import com.zosh.modal.Order;
import com.zosh.modal.User;
import com.zosh.modal.Wallet;

public interface WalletService {

    Wallet getUserWallet(User user);
    Wallet addBalance(Wallet wallet, Long money); // Removed 'static' to be consistent with implementation
    Wallet findWalletById(Long id) throws Exception;
    Wallet walletToWalletTransfer(User sender, Wallet receiverWallet, Long amount);
    Wallet payOrderPayment(Order order, User user);
}

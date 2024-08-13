package com.zosh.controller;

import com.zosh.modal.User;
import com.zosh.modal.Wallet;
import com.zosh.modal.Withdrawal;
import com.zosh.service.UserService;
import com.zosh.service.WalletService;
import com.zosh.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WithdrawalController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Autowired
    private WithdrawalService withdrawalService;

    @PostMapping("/withdrawal/{amount}")
    public ResponseEntity<Withdrawal> withdrawalRequest(@PathVariable Long amount, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet userWallet = walletService.getUserWallet(user);

        Withdrawal withdrawal = withdrawalService.requestyWithdrawal(amount, user);
        walletService.addBalance(userWallet, -withdrawal.getAmount());

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @PatchMapping("/admin/withdrawal/{id}/proceed/{accept}")
    public ResponseEntity<Withdrawal> proceedWithdrawal(@PathVariable Long id, @PathVariable Boolean accept, @RequestHeader("Authorization") String jwt) throws Exception {
        userService.findUserProfileByJwt(jwt); // Ensure the user is authenticated
        Withdrawal withdrawal = withdrawalService.procedWithWithdrawal(id, accept);

        if (!accept) {
            Wallet userWallet = walletService.getUserWallet(withdrawal.getUser());
            walletService.addBalance(userWallet, withdrawal.getAmount());
        }

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/withdrawal")
    public ResponseEntity<List<Withdrawal>> getWithdrawalHistory(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Withdrawal> withdrawals = withdrawalService.getUsersWithdrawalHistory(user);
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }

    @GetMapping("/admin/withdrawal")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawalRequests(@RequestHeader("Authorization") String jwt) throws Exception {
        userService.findUserProfileByJwt(jwt); // Ensure the user is authenticated
        List<Withdrawal> withdrawals = withdrawalService.getAllWithdrawalRequest();
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }
}

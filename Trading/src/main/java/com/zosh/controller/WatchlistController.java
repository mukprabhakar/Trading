package com.zosh.controller;

import com.zosh.modal.Coin;
import com.zosh.modal.User;
import com.zosh.modal.Watchlist;
import com.zosh.service.CoinService;
import com.zosh.service.UserService;
import com.zosh.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private UserService userService;

    @Autowired
    private CoinService coinService;

    @GetMapping("/user")
    public ResponseEntity<Watchlist> getUserWatchlist(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Watchlist watchlist = watchlistService.findUserWatchlist(user.getId());
        return ResponseEntity.ok().body(watchlist);
    }

//    Uncomment and modify this method if needed
//    @PostMapping("/create")
//    public ResponseEntity<Watchlist> createWatchlist(@RequestHeader("Authorization") String jwt) throws Exception {
//        User user = userService.findUserProfileByJwt(jwt);
//        Watchlist createwatchlist = watchlistService.createWatchlist(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createwatchlist);
//    }

    @GetMapping("/{watchlistId}")
    public ResponseEntity<Watchlist> getWatchlistById(@PathVariable Long watchlistId) throws Exception {
        Watchlist watchlist = watchlistService.findById(watchlistId);
        return ResponseEntity.ok(watchlist);
    }

    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<Coin> addItemToWatchlist(@RequestHeader("Authorization") String jwt, @PathVariable String coinId) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Coin coin = coinService.findById(coinId);
        Coin addedCoin = watchlistService.addItemToWatchlist(coin, user);
        return ResponseEntity.ok(addedCoin);
    }
}

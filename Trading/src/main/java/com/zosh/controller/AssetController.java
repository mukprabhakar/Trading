package com.zosh.controller;

import com.zosh.modal.Asset;
import com.zosh.modal.User;
import com.zosh.service.AssetService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private UserService userService;

    @GetMapping("/{assetId}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long assetId) throws Exception {
        Asset asset = assetService.getAssetById(assetId);
        return ResponseEntity.ok().body(asset);
    }

    @GetMapping("/coin/{coinId}/user")
    public ResponseEntity<Asset> getUserAssetByUserIdAndCoinId(@PathVariable String coinId,@RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserProfileByJwt(jwt);
        Asset asset = assetService.findAssetByUserIdAndCoinId(user.getId(),coinId);
        return ResponseEntity.ok().body(asset);
    }

    @GetMapping()
    public ResponseEntity<List<Asset>> getAssetsForUser(@RequestHeader("Authorization") String  jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Asset> assets=assetService.getUsersAsset(user.getId());
        return ResponseEntity.ok().body(assets);
    }
}

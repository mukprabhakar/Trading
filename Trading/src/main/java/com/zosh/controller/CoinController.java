package com.zosh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zosh.modal.Coin;
import com.zosh.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    ResponseEntity<List<Coin>>getCoinList(@RequestParam(required = false, name="page") int page) throws Exception {
        List<Coin> coins=coinService.getCoinList(page);
        return new ResponseEntity<>(coins, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{coinId}/chart")
    ResponseEntity<JsonNode>getMarketChart(@PathVariable String coinjId, @RequestParam("days") int days) throws Exception {
        String res =coinService.getMarketChart(coinjId, days);
        JsonNode jsonNode=objectMapper.readTree(res);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    ResponseEntity<JsonNode> searchCoin(@RequestParam("q") String keyword) throws Exception {
        String coins=coinService.searchCoin(keyword);
        JsonNode jsonNode=objectMapper.readTree(coins);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/top50")
    ResponseEntity<JsonNode> getTop50CoinByMarketCapRank() throws Exception {
        String coins=coinService.getTop50CoinsByMarketCapRank();
        JsonNode jsonNode=objectMapper.readTree(coins);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/treading")
    ResponseEntity<JsonNode> getTreadingCoin() throws Exception {
        String coins=coinService.getTreadingCoins();
        JsonNode jsonNode=objectMapper.readTree(coins);
        return ResponseEntity.ok(jsonNode);
    }
}

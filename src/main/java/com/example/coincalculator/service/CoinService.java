package com.example.coincalculator.service;

import com.example.coincalculator.service.model.CoinRequest;
import com.example.coincalculator.service.model.CoinResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoinService {

    private static final List<Double> VALID_DENOMINATIONS = List.of(0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0);

    public CoinResponse calculateMinimumCoins(CoinRequest request) {
        double targetAmount = request.getTargetAmount();
        List<Double> denominations = new ArrayList<>(request.getDenominations());

        if (targetAmount <= 0 || targetAmount > 10000.0) {
            throw new IllegalArgumentException("Please enter a valid target amount (range: 0.01 to 10,000).");
        }

        if (denominations.isEmpty()) {
            throw new IllegalArgumentException("No coin denominations are provided, please provide at least one valid denomination.");
        }

        for (double coin : denominations) {
            if (!VALID_DENOMINATIONS.contains(coin)) {
                throw new IllegalArgumentException("Invalid coin denomination:" + coin + ". Valid denominations include:" + VALID_DENOMINATIONS);
            }
        }

        int target = (int) Math.round(targetAmount * 100);
        List<Integer> intDenominations = new ArrayList<>();
        for (double coin : denominations) {
            intDenominations.add((int) Math.round(coin * 100));
        }

        int[] dp = new int[target + 1];
        int[] lastUsedCoin = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for (int coin : intDenominations) {
            for (int x = coin; x <= target; x++) {
                if (dp[x - coin] != Integer.MAX_VALUE && dp[x] > dp[x - coin] + 1) {
                    dp[x] = dp[x - coin] + 1;
                    lastUsedCoin[x] = coin;
                }
            }
        }

        if (dp[target] == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Cannot use the coin denominations provided to raise the target amount.");
        }

        List<Double> coinsUsed = new ArrayList<>();
        while (target > 0) {
            int coin = lastUsedCoin[target];
            coinsUsed.add(coin / 100.0);
            target -= coin;
        }

        Collections.sort(coinsUsed);
        return new CoinResponse(coinsUsed);
    }
}

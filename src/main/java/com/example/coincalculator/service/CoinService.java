package com.example.coincalculator.service;

import com.example.coincalculator.service.model.CoinRequest;
import com.example.coincalculator.service.model.CoinResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoinService {

    public CoinResponse calculateMinimumCoins(CoinRequest request) {
        double targetAmount = request.getTargetAmount();
        List<Double> denominations = request.getDenominations();

        if (targetAmount < 0 || targetAmount > 10000.0) {
            throw new IllegalArgumentException("目标金额必须在 0 到 10,000 之间。");
        }

        List<Double> coinsUsed = new ArrayList<>();
        Collections.sort(denominations, Collections.reverseOrder());

        for (double coin : denominations) {
            while (targetAmount >= coin) {
                targetAmount = Math.round((targetAmount - coin) * 100.0) / 100.0;
                coinsUsed.add(coin);
            }
        }

        return new CoinResponse(coinsUsed);
    }
}

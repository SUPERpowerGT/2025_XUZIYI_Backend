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
            throw new IllegalArgumentException("The target amount must be between 0 and 10,000.");
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

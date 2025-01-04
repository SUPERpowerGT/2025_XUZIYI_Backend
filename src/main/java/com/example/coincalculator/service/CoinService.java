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
        List<Double> denominations = request.getDenominations();

        if (targetAmount < 0 || targetAmount > 10000.0) {
            throw new IllegalArgumentException("The target amount must be between 0 and 10,000.");
        }

        for (double coin : denominations) {
            if (!VALID_DENOMINATIONS.contains(coin)) {
                throw new IllegalArgumentException("Invalid coin denomination: " + coin + ". Valid denominations are: " + VALID_DENOMINATIONS);
            }
        }

        Collections.sort(denominations, Collections.reverseOrder());

        List<Double> coinsUsed = new ArrayList<>();

        for (double coin : denominations) {
            while (targetAmount >= coin) {
                targetAmount = Math.round((targetAmount - coin) * 100.0) / 100.0;
                coinsUsed.add(coin);
            }
        }

        if (targetAmount > 0) {
            throw new IllegalArgumentException("Unable to raise the target amount with the coin denominations provided.");
        }

        Collections.sort(coinsUsed);

        return new CoinResponse(coinsUsed);
    }
}

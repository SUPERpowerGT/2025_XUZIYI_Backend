package com.example.coincalculator.service.model;

import java.util.List;

public class CoinResponse {
    private List<Double> coinsUsed;

    public CoinResponse(List<Double> coinsUsed) {
        this.coinsUsed = coinsUsed;
    }

    public List<Double> getCoinsUsed() {
        return coinsUsed;
    }

    public void setCoinsUsed(List<Double> coinsUsed) {
        this.coinsUsed = coinsUsed;
    }
}

package com.example.coincalculator.service.model;

import java.util.List;

public class CoinRequest {
    private double targetAmount;
    private List<Double> denominations;

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public List<Double> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Double> denominations) {
        this.denominations = denominations;
    }
}

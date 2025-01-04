package com.example.coincalculator;

import com.example.coincalculator.service.CoinService;
import com.example.coincalculator.service.model.CoinRequest;
import com.example.coincalculator.service.model.CoinResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CoinServiceTest {
    private final CoinService coinService = new CoinService();

    @Test
    void testCalculateMinimumCoins() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(7.03);
        request.setDenominations(Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0));

        CoinResponse response = coinService.calculateMinimumCoins(request);

        assertEquals(Arrays.asList(0.01, 0.01, 0.01, 1.0, 1.0, 5.0), response.getCoinsUsed());
    }

    @Test
    void testInvalidTargetAmount() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(10001.0);
        request.setDenominations(Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            coinService.calculateMinimumCoins(request);
        });

        assertEquals("The target amount must be between 0 and 10,000.", exception.getMessage());
    }

    @Test
    void testCalculateMinimumCoinsWithLargeTargetAmount() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(103.0);
        request.setDenominations(Arrays.asList(1.0, 2.0, 50.0));

        CoinResponse response = coinService.calculateMinimumCoins(request);

        assertEquals(Arrays.asList(1.0, 2.0, 50.0, 50.0), response.getCoinsUsed());
    }
}

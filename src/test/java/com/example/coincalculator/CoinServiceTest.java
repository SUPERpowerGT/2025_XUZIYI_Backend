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
    void testCalculateMinimumCoins_ValidCase() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(126.5);
        request.setDenominations(Arrays.asList(0.5, 2.0, 5.0, 10.0, 50.0, 100.0));

        CoinResponse response = coinService.calculateMinimumCoins(request);

        assertEquals(Arrays.asList(0.5, 2.0, 2.0, 2.0, 10.0, 10.0, 100.0), response.getCoinsUsed());
    }

    @Test
    void testInvalidDenominations() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(126.5);
        request.setDenominations(Arrays.asList(3.0, 5.0));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            coinService.calculateMinimumCoins(request);
        });

        assertEquals("Invalid coin denomination:3.0. Valid denominations include:[0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0]", exception.getMessage());
    }

    @Test
    void testUnconstructibleTargetAmount() {
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(126.25);
        request.setDenominations(Arrays.asList(0.5, 2.0, 5.0));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            coinService.calculateMinimumCoins(request);
        });

        assertEquals("Cannot use the coin denominations provided to raise the target amount.", exception.getMessage());
    }
}

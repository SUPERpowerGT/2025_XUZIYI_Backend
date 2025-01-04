package com.example.coincalculator.resources;

import com.example.coincalculator.service.CoinService;
import com.example.coincalculator.service.model.CoinRequest;
import com.example.coincalculator.service.model.CoinResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/calculate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoinCalculatorResource {

    private final CoinService coinService = new CoinService();

    @POST
    public Response calculateCoins(CoinRequest request) {
        try {
            CoinResponse response = coinService.calculateMinimumCoins(request);
            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}

package com.example.tollPriceCalculator.tollPriceCalculator.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class TollGuruService {

    private static final String API_URL = "https://apis.tollguru.com/toll/v2/origin-destination-waypoints";
    private static final String API_KEY = "7p3Mf7gNfPM79Lrr7m8mTH2PjTM4HQgm";
    private final OkHttpClient client;

    public TollGuruService() {
        this.client = new OkHttpClient();
    }

    @SuppressWarnings("deprecation")
	public String calculateToll(String fromAddress, String toAddress, String waypointAddress) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        String requestBodyString = String.format(
                "{  \"from\": {    \"address\": \"%s\"  },  \"to\": {    \"address\": \"%s\"  },  \"serviceProvider\": \"here\",  \"waypoints\": [    {      \"address\": \"%s\"    }  ],  \"vehicle\": {    \"type\": \"2AxlesAuto\"  }}",
                fromAddress, toAddress, waypointAddress
        );

        RequestBody body = RequestBody.create(mediaType, requestBodyString);
        Request request = new Request.Builder()
            .url(API_URL)
            .post(body)
            .addHeader("Content-Type", "application/json")
            .addHeader("x-api-key", API_KEY)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + " with response body: " + response.body().string());
            }
            return response.body().string();
        }
    }
}
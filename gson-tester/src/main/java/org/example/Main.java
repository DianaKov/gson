package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        String url = "https://api.monobank.ua/bank/currency";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String response = String.join("", in.lines().toArray(String[]::new));

                double usdRate = getExchangeRate(response);
                double uahRate = 1 / usdRate;
                System.out.println("USD rate: " + usdRate);
                System.out.println("UAH rate: " + uahRate);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static double getExchangeRate(String response) {
        Gson gson = new Gson();
        JsonObject[] rates = gson.fromJson(response, JsonObject[].class);

        for (JsonObject rate : rates) {
            if (rate.get("currencyCodeA").getAsInt() == 840 && rate.get("currencyCodeB").getAsInt() == 980) {
                return rate.get("rateBuy").getAsDouble();
            }
        }
        throw new RuntimeException("Exchange rate not found");
    }
}

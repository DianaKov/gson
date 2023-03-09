package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {
        String url = "https://api.monobank.ua/bank/currency";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        Gson gson = new Gson();
        JsonObject[] rates = gson.fromJson(response.toString(), JsonObject[].class);
        for (JsonObject rate : rates) {
            if (rate.get("currencyCodeA").getAsInt() == 840 && rate.get("currencyCodeB").getAsInt() == 980) {
                double usdRate = rate.get("rateBuy").getAsDouble();
                double uahRate = 1 / usdRate;
                System.out.println("USD rate: " + usdRate);
                System.out.println("UAH rate: " + uahRate);
                break;
            }
        }
    }
}
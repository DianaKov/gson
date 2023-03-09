package org.example;

import com.google.gson.*;

class Recipient {
    String name;
    String order;
    Order[] orders;

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class Order {
    String name;
    double price;

    @Override
    public String toString() {
        return super.toString();
    }
}

public class App {
    static String JSON = """
        {
            "recipient": {
                "name": "dsfdsf",
                "order": "34543545"
            },
            "orders": [
                {
                    "name": "Xxxx",
                    "price": 79.5
                },
                {
                    "name": "Yyyyyy",
                    "price": 55.5
                },
                {
                    "name": "ddfdfg",
                    "price": 90
                }
            ]
        }
    """;

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Recipient person = gson.fromJson(JSON, Recipient.class);
        System.out.println(person);

        String json = gson.toJson(person);
        System.out.println(json);
    }
}
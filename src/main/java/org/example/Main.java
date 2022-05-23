package org.example;

import com.jsoniter.JsonIterator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.damages.Type;
import org.example.damages.Types;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/type/")
                .get().build();
        try(Response response = client.newCall(request).execute()) {
            String body = response.body().string();
            System.out.println(body);
            System.out.println(JsonIterator.deserialize(body, Types.class));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
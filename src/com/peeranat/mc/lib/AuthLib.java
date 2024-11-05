package com.peeranat.mc.lib;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.google.gson.Gson;
import com.peeranat.mc.ITAuthMain;

public class AuthLib {

	public static AuthResponse sendPostRequest(String username, String password) {
        String url = ITAuthMain.getInstance().getConfig().getString("AUTH_URL");
        
        // Build JSON payload
        String jsonPayload = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
        
        // Create HttpClient
        HttpClient client = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(jsonPayload))
            .build();
        
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() != 200) {
				return null;
			}
			Gson gson = new Gson();
			AuthResponse authResponse = gson.fromJson(response.body(), AuthResponse.class);
			return authResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
	
}

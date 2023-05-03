package com.damian.controller;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hawolt.virtual.leagueclient.authentication.Session;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AccountInfo {
    private String username;
    private String password;
    private Session session;
    private String sessionToken;
    private String accessToken;
    private String puuid;
    private String accountId;
    private String blueEssence;
    private String riotPoints;
    private VirtualClientInstance virtualClientInstance;
    private String region;
    private String location;
    private UrlsGetter urlsGetter;
    private HttpClient client = HttpClient.newHttpClient();
    public AccountInfo(String username, String password) {
        this.username = username;
        this.password = password;
        this.virtualClientInstance = new VirtualClientInstance(username, password);
        this.session = this.virtualClientInstance.virtualLeagueClient.getSession();
        this.setSessionToken();
        this.setRegion();
        this.setAccessToken();
        this.urlsGetter = new UrlsGetter(this.region.substring(0, 3), this.puuid, this.location, this.accountId);
        try {
            this.setWalletBalance();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setSessionToken() {
        this.sessionToken = this.virtualClientInstance.virtualLeagueClient.getSession().get("session_token");
    }
    public void setAccessToken() {
        this.accessToken = this.virtualClientInstance.virtualRiotClient.getRiotClientSupplier().get("access_token");
    }

    public void setRegion() {
        this.region = this.virtualClientInstance.virtualRiotClient.getRiotClientUser().getDataRegion();
    }

    public void setWalletBalance() throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(this.urlsGetter.getBeAndRpUrl()))
                .headers("Authorization", "Bearer " + this.accessToken)
                .GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement jsonElement = gson.fromJson(response.body(), JsonElement.class);
        this.blueEssence = jsonElement.getAsJsonObject().get("ip").getAsString();
        this.riotPoints = jsonElement.getAsJsonObject().get("rp").getAsString();
    }
}

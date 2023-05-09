package com.damian.controller;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hawolt.virtual.leagueclient.authentication.Session;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Getter
@Setter
public class AccountInfo {
    private String username;
    private String password;
    private Session session;
    private String sessionToken;
    private String accessToken;
    private String puuid;
    private Long accountId;
    private String blueEssence;
    private String riotPoints;
    private VirtualClientInstance virtualClientInstance;
    private String region;
    private String location;
    private UrlsGetter urlsGetter;
    private HttpClient client = HttpClient.newHttpClient();
    private int championsCount;
    JsonElement champions;
    public AccountInfo(String username, String password) {
        this.username = username;
        this.password = password;
        this.virtualClientInstance = new VirtualClientInstance(username, password);
        this.session = this.virtualClientInstance.virtualLeagueClient.getSession();
        this.setSessionToken();
        this.setRegion();
        this.setAccessToken();
        this.setLocation();
        this.setAccountId();
        this.setPuuid();
        this.urlsGetter = new UrlsGetter(this.region, this.puuid, this.location, this.accountId);
        try {
            this.setWalletBalance();
            this.setChampions();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void setPuuid() {
        this.puuid = this.virtualClientInstance.virtualRiotClient.getRiotClientUser().getSub();
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
    public void setLocation(){
        this.location = "lolriot.aws-euc1-prod.eun1";
    }
    public void setAccountId(){
        this.accountId = this.virtualClientInstance.virtualRiotClient.getRiotClientUser().getDataUserId();
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
    public void setChampions() throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(this.urlsGetter.getChampionsUrl()))
                .headers("Authorization", "Bearer " + this.sessionToken)
                .GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        this.champions = gson.fromJson(response.body(), JsonElement.class);
    }
}

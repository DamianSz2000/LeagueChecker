package com.damian.controller;

import com.hawolt.generic.data.Platform;
import com.hawolt.virtual.leagueclient.authentication.Session;

public class AccountInfo {
    public String username;
    public String password;
    public Session session;
    public String sessionToken;
    public VirtualClientInstance virtualClientInstance;
    public Platform region;
    AccountInfo(String username, String password) {
        this.username = username;
        this.password = password;
        this.virtualClientInstance = new VirtualClientInstance(username, password);
        this.session = this.virtualClientInstance.virtualLeagueClient.getSession();
        this.setSessionToken();
        this.setRegion();
    }

    public void setSessionToken() {
        this.sessionToken = this.virtualClientInstance.virtualLeagueClient.getSession().get("session_token");
    }

    public void setRegion() {
        this.region = this.session.platform;
    }
}

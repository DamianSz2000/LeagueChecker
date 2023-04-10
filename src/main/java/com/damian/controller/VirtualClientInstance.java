package com.damian.controller;

import com.hawolt.authentication.LocalCookieSupplier;
import com.hawolt.logger.Logger;
import com.hawolt.manifest.RMANCache;
import com.hawolt.virtual.leagueclient.VirtualLeagueClient;
import com.hawolt.virtual.leagueclient.VirtualLeagueClientInstance;
import com.hawolt.virtual.leagueclient.exception.LeagueException;
import com.hawolt.virtual.riotclient.VirtualRiotClient;
import com.hawolt.virtual.riotclient.VirtualRiotClientInstance;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;


public class VirtualClientInstance {
    public VirtualRiotClientInstance virtualRiotClientInstance;
    public VirtualRiotClient virtualRiotClient;
    public VirtualLeagueClientInstance virtualLeagueClientInstance;
    public VirtualLeagueClient virtualLeagueClient;
    public VirtualClientInstance(String user, String pass) {
        RMANCache.active = true;
        LocalCookieSupplier localCookieSupplier = new LocalCookieSupplier();
        VirtualRiotClientInstance virtualRiotClientInstance = VirtualRiotClientInstance.create(localCookieSupplier);
        try {
            VirtualRiotClient virtualRiotClient = virtualRiotClientInstance.login(user, pass);
            VirtualLeagueClientInstance virtualLeagueClientInstance = virtualRiotClient.createVirtualLeagueClientInstance();
            CompletableFuture<VirtualLeagueClient> virtualLeagueClientFuture = virtualLeagueClientInstance.login(true, false);
            virtualLeagueClientFuture.whenComplete(((virtualLeagueClient, throwable) -> {
                if (throwable != null) throwable.printStackTrace();
                else {
                    this.virtualRiotClientInstance = virtualRiotClientInstance;
                    this.virtualRiotClient = virtualRiotClient;
                    this.virtualLeagueClientInstance = virtualLeagueClientInstance;
                    this.virtualLeagueClient = virtualLeagueClient;
                }
            }));
        } catch (IOException e) {
            Logger.error(e);
        } catch (LeagueException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.damian.controller;

public class UrlsGetter {
    private String region;
    private String puuid;
    private String location;
    private String accountId;

    public UrlsGetter(String region, String puuid, String location, String accountId) {
        this.region = region;
        this.puuid = puuid;
        this.location = location;
        this.accountId = accountId;
    }

    public String getBeAndRpUrl() {
        return "https://" + region + ".store.leagueoflegends.com/storefront/v2/wallet?language=en_GB";
    }

    public String getChampionsUrl() {
        return "https://" + region + "-red.lol.sgp.pvp.net/lolinventoryservice-ledge/v2/inventoriesWithLoyalty?puuid=" + puuid + "&inventoryTypes=CHAMPION&location=" + location + "&accountId=" + accountId + "&signed=true";
    }

    public String getSkinsUrl() {
        return "https://" + region + "-red.lol.sgp.pvp.net/lolinventoryservice-ledge/v2/inventoriesWithLoyalty?puuid=" + puuid + "&inventoryTypes=CHAMPION_SKIN&location=" + location + "&accountId=" + accountId + "&signed=true";
    }

    public String getRankedInfoUrl() {
        return "https://" + region + "-red.lol.sgp.pvp.net/leagues-ledge/v2/signedRankedStats";
    }
}

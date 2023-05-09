package com.damian.controller;

public class UrlsGetter {
    private String region;
    private String puuid;
    private String location;
    private Long accountId;

    public UrlsGetter(String region, String puuid, String location, Long accountId) {
        this.region = region;
        this.puuid = puuid;
        this.location = location;
        this.accountId = accountId;
    }

    public String getBeAndRpUrl() {
        return "https://" + region.substring(0, 3) + ".store.leagueoflegends.com/storefront/v2/wallet?language=en_GB";
    }

    public String getChampionsUrl() {
        if(this.region.equals("EUN1")){
            this.region = "eune";
        }
        return "https://" + region + "-red.lol.sgp.pvp.net/lolinventoryservice-ledge/v2/inventoriesWithLoyalty?puuid=" + puuid + "&inventoryTypes=CHAMPION&location=" + location + "&accountId=" + accountId;
    }

    public String getSkinsUrl() {
        return "https://" + region + "-red.lol.sgp.pvp.net/lolinventoryservice-ledge/v2/inventoriesWithLoyalty?puuid=" + puuid + "&inventoryTypes=CHAMPION_SKIN&location=" + location + "&accountId=" + accountId + "&signed=true";
    }

    public String getRankedInfoUrl() {
        return "https://" + region + "-red.lol.sgp.pvp.net/leagues-ledge/v2/signedRankedStats";
    }
}

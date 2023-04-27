package com.damian;

import com.damian.view.MainView;
import com.damian.controller.VirtualClientInstance;
import com.hawolt.logger.Logger;
import com.hawolt.virtual.leagueclient.authentication.Session;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        MainView view = new MainView();
        view.createAndShowGUI();
        VirtualClientInstance client = new VirtualClientInstance("kretopeak2", "720912piotr");
        Session session = client.virtualLeagueClient.getSession();


    }
}
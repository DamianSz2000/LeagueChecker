package com.damian;

import com.damian.controller.AccountInfo;
import com.damian.view.MainView;
import com.damian.controller.VirtualClientInstance;

import java.io.IOException;
import java.net.URISyntaxException;


public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        view.createAndShowGUI();
        AccountInfo accountInfo = new AccountInfo();

    }
}
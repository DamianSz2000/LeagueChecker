package com.damian;

import com.damian.controller.AccountInfo;
import com.damian.view.MainView;


public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        view.createAndShowGUI();
        AccountInfo accountInfo = new AccountInfo();
        System.out.println(accountInfo.getBlueEssence());
        System.out.println(accountInfo.getRiotPoints());

    }
}
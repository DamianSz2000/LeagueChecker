package com.damian;

import com.damian.controller.AccountInfo;
import com.damian.model.Connect;
import com.damian.view.MainView;


public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        view.createAndShowGUI();
        AccountInfo accountInfo = new AccountInfo("", "");
        String json = accountInfo.returnAsJSONString();
        System.out.println(json);
    }
}
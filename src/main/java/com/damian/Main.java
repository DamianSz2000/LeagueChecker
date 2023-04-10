package com.damian;

import com.damian.view.MainView;
import com.damian.controller.VirtualClientInstance;
public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        view.createAndShowGUI();
        VirtualClientInstance client = new VirtualClientInstance("kretopeak2", "720912piotr");
    }
}
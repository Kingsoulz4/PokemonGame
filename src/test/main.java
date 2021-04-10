/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.Vector;

import controllers.TimeControl;
import sun.audio.*;
import userinterface.HomeScreen;
import userinterface.MainFrame;

/**
 *
 * @author DatHoang
 */
public class main {
    public static void main(String[] args) throws IOException {
        int numOfIcon =21;
        int column = 10;
        int row = 10;
        MainFrame frameTest = new MainFrame("Pokemon");
        TimeControl timeControl = new TimeControl(frameTest);
        frameTest.setTimeControl(timeControl);
        timeControl.setResume(true);
        timeControl.start();
        new Thread(frameTest).start();
        frameTest.showWindow();
//        //Music music = new Music();  
//    	HomeScreen hScreen = new HomeScreen("Home");
//    	hScreen.showWindow();
        
    }
}


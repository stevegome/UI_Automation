package Interfaces;


import Pages.HMIWebScreen.LoginScreen;
import Pages.HMIWebScreen.DashboardScreen;
import Pages.HMIWebScreen.ConfigurationScreen;


public interface ClassObjects {


    //HMI WEB SCREENS
    LoginScreen login = new LoginScreen();
    DashboardScreen dashboard = new DashboardScreen();
    ConfigurationScreen configuration = new ConfigurationScreen();


}

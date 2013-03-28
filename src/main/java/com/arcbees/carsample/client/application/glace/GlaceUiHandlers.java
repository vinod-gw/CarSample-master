package com.arcbees.carsample.client.application.glace;



import com.gwtplatform.mvp.client.UiHandlers;

public interface GlaceUiHandlers extends UiHandlers {
   // void login(String username, String password, String accountid);
    void login(String username, String password, String accountid);

	void glaceLogin(String username, String password, String accountid);
}
package com.arcbees.carsample.client.application.glace;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class AccessView extends ViewImpl implements AccessPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, AccessView> {
	}
	@UiField
	TextBox email1;
	@UiField
	TextBox email2;
	@UiField
	TextBox email3;
	@UiField
	RadioButton password;
	@UiField
	RadioButton accid;
	@UiField
	RadioButton username;
	@UiField
	RadioButton other;
	@UiField
	Button glacelogin;

	@UiField
	HTMLPanel hide1;
	@UiField
	HTMLPanel hide2;
	@UiField
	HTMLPanel hide3;
	
	
	

	public HTMLPanel getHide1() {
		return hide1;
	}

	public Widget getWidget() {
		return widget;
	}

	public TextBox getEmail3() {
		return email3;
	}

	public HTMLPanel getHide3() {
		return hide3;
	}

	public void setHide1(HTMLPanel hide1) {
		this.hide1 = hide1;
	}

	public HTMLPanel getHide2() {
		return hide2;
	}


	@Inject
	public AccessView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	public RadioButton getPassword() {
		return password;
	}

	public RadioButton getAccid() {
		return accid;
	}


	public RadioButton getUsername() {
		return username;
	}


	public RadioButton getOther() {
		return other;
	}


	public TextBox getEmail1() {
		return email1;
	}

	public TextBox getEmail2() {
		return email2;
	}


	public Button getGlacelogin() {
		return glacelogin;
	}


	@Override
	public Widget asWidget() {
		
		return widget;
	}
}
 
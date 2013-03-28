package com.arcbees.carsample.client.application.glace;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.arcbees.carsample.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class AccessPresenter extends
		Presenter<AccessPresenter.MyView, AccessPresenter.MyProxy> {

	public interface MyView extends View {
		public RadioButton getPassword();
		public RadioButton getUsername();
		public RadioButton getOther();
		public HTMLPanel getHide2();
		public HTMLPanel getHide1();
		public HTMLPanel getHide3();
		public RadioButton getAccid();
		
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.access)
	public interface MyProxy extends ProxyPlace<AccessPresenter> {
	}

	@Inject
	public AccessPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReset() {
		super.onReset();
		getView().getPassword().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

                    getView().getHide1().setHeight("100px");
                    getView().getHide2().setHeight("0px");
                    getView().getHide3().setHeight("0px");
                   
            }

    });
		getView().getAccid().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

                    getView().getHide1().setHeight("0px");
                    getView().getHide2().setHeight("0px");
                    getView().getHide3().setHeight("100px");
                   
            }

    });
		getView().getUsername().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
            	getView().getHide3().setHeight("0px");
                    getView().getHide1().setHeight("0px");
                    getView().getHide2().setHeight("0px");
                   
            }

    });
		getView().getOther().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

                    getView().getHide1().setHeight("0px");
                    getView().getHide2().setHeight("100px");
                    getView().getHide3().setHeight("0px");
                   
            }

    });
          
	}
}

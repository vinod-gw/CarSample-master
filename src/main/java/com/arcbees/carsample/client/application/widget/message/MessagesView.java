package com.arcbees.carsample.client.application.widget.message;

import com.arcbees.carsample.client.application.widget.message.ui.MessageWidget;
import com.arcbees.carsample.client.application.widget.message.ui.MessageWidgetFactory;
import com.arcbees.core.client.mvp.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MessagesView extends ViewImpl implements MessagesPresenter.MyView {
    public interface Binder extends UiBinder<Widget, MessagesView> {
    }

    @UiField
    HTMLPanel messages;

    private final MessageWidgetFactory messageWidgetFactory;

    @Inject
    public MessagesView(final Binder binder, final MessageWidgetFactory messageWidgetFactory) {
        this.messageWidgetFactory = messageWidgetFactory;

        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void addMessage(Message message) {
        MessageWidget messageWidget = messageWidgetFactory.createMessage(message);
        messages.add(messageWidget);
    }
}

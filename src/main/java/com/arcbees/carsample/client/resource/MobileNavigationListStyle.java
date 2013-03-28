package com.arcbees.carsample.client.resource;

import com.google.gwt.user.cellview.client.CellList;

public interface MobileNavigationListStyle extends CellList.Resources {
    @Source({CellList.Style.DEFAULT_CSS, "com/arcbees/carsample/client/mobileNavigationListStyle.css"})
    ListStyle cellListStyle();

    interface ListStyle extends CellList.Style {
    }
}


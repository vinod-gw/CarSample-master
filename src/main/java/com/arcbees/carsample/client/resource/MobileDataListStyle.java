package com.arcbees.carsample.client.resource;

import com.google.gwt.user.cellview.client.CellList;

public interface MobileDataListStyle extends CellList.Resources {
    @Source({CellList.Style.DEFAULT_CSS, "com/arcbees/carsample/client/mobileDataListStyle.css"})
    ListStyle cellListStyle();

    interface ListStyle extends CellList.Style {
    }
}


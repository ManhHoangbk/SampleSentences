package com.koolsoft.samplesentences.client.basic;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.koolsoft.samplesentences.client.basic.BasicViewImpl.Layout;

public interface BasicView extends IsWidget {

	Widget asWidget();
	Layout getLayout();
}

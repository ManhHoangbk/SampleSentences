package com.koolsoft.samplesentences.client.home;

import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.RadioButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class HomeItemView extends FlowPanel  {

	private static HomeItemViewUiBinder uiBinder = GWT.create(HomeItemViewUiBinder.class);

	interface HomeItemViewUiBinder extends UiBinder<Widget, HomeItemView> {
	}
	@UiField CheckBox rb;
	@UiField HTML sample;
	public HomeItemView(String text,int i) {
		this.add(uiBinder.createAndBindUi(this));
		rb.setName("rb"+i);
		rb.setHTML(text);
		
	}


}

package com.koolsoft.samplesentences.client.home;

import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class HomeInputItem extends FlowPanel {

	private static HomeInputItemUiBinder uiBinder = GWT.create(HomeInputItemUiBinder.class);

	interface HomeInputItemUiBinder extends UiBinder<Widget, HomeInputItem> {
	}

	@UiField
	TextBox sentence;
	@UiField
	Button btnRemove;

	public HomeInputItem() {
		this.add(uiBinder.createAndBindUi(this));
		this.sentence.getElement().setAttribute("placeholder", "Nhập câu....");

	}

	public TextBox getSentence() {
		return sentence;
	}

	public Button getBtnRemove() {
		return btnRemove;
	}

//	public void ClickRemovePanel() {
//		btnRemove.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				btnRemove.setVisible(false);
//			}
//		});
//		
//	}

}

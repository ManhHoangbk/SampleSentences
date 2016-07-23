package com.koolsoft.samplesentences.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoadingDialog {

	private static LoadingDialogUiBinder uiBinder = GWT
			.create(LoadingDialogUiBinder.class);
	@UiField public VerticalPanel  mainPanel;
	@UiField protected HTML htmlLoading;
	interface LoadingDialogUiBinder extends UiBinder<Widget, LoadingDialog> {
	}

	public LoadingDialog() {
		super();
		RootPanel.get().add(uiBinder.createAndBindUi(this));
		mainPanel.setVisible(false);
		htmlLoading.setHTML(
				"<p class='loader' style='text-align:center;'></p>" );
	}
	
	public LoadingDialog(String message) {
		super();
		uiBinder.createAndBindUi(this);
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		RootPanel.get().add(mainPanel);
		mainPanel.setVisible(false);
		htmlLoading.setHTML(
				"<p style='text-align:center;'>"+message+"</p>" );
	}
	
	public void show(String message) {
		this.show();
		htmlLoading.setHTML(
				"<p style='text-align:center;'>"+message+"</p>" );
	}
	
	public void show() {
		hideTimer.schedule(300000);
		mainPanel.removeFromParent();
		RootPanel.get().add(mainPanel);
		htmlLoading.setHTML(
				"<p class='loader' style='text-align:center;'></p>" );
		mainPanel.setVisible(true);
	}
	
	public void hide() {
		mainPanel.removeFromParent();
		mainPanel.setVisible(false);
	}
	
	public Timer hideTimer = new Timer() {
		
		@Override
		public void run() {
			hide();
		}
	};
}

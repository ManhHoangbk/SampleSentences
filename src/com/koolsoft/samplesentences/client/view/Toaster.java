package com.koolsoft.samplesentences.client.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.koolsoft.samplesentences.client.SampleSentences;

public class Toaster {
	private static PopupPanel dialogs = new PopupPanel(false, false);
	private static HorizontalTouchPanel panel = new HorizontalTouchPanel();
	private static HTML html = null;
	
	private static Timer timer = new Timer() {
		@Override
		public void run() {
			dialogs.hide();
		}
	};

	public static void showToast(String message, boolean isLong, int pos) {
		if (SampleSentences.phoneGap.isPhoneGapDevice())
			toast(message, isLong,pos);
		else
			showToastX(message, isLong);
	}

	private static native void toast(String message, boolean isLong, int position)/*-{
		if(isLong) {
			if(position == 1)
				$wnd.plugins.toast.showLongTop(message);
			else if(position == 2)
				$wnd.plugins.toast.showLongCenter(message);
			else if(position == 3)
				$wnd.plugins.toast.showLongBottom(message);
		}
		else {
			if(position == 1)
			$wnd.plugins.toast.showShortTop(message);
		else if(position == 2)
			$wnd.plugins.toast.showShortCenter(message);
		else if(position == 3)
			$wnd.plugins.toast.showShortBottom(message);
		}
	}-*/;
	
	public static void showToast(String message, boolean isLong) {
		showToast(message, isLong, 1);
	}
	
	public static void showToastX(String message, boolean isLong) {
		if (SampleSentences.phoneGap.isPhoneGapDevice())
			showToast(message);
		else {
			showToastX(message);
			timer.schedule(isLong ? 5000 : 2000);
		}
	}
	
	public static void showToast(String msg) {
		Toaster.showToast(msg, false, 1);
	}
	
	@SuppressWarnings("deprecation")
	private static void showToastX(String message) {
		if (html == null) {
			html = new HTML();
			panel.add(html);
			panel.setSpacing(5);
			dialogs.add(panel);
			DOM.setElementAttribute(dialogs.getElement(), "style", "background-color: white; z-index : 10000;");
		}
		html.setHTML("<font size='2'>" + message + "</font>");
		dialogs.show();
		Element elem = dialogs.getElement();
		elem.getStyle().setPropertyPx("left", 0);
		elem.getStyle().setPropertyPx("top",100);

		int left = (Window.getClientWidth() - dialogs.getOffsetWidth()) >> 1;
		//int top = (Window.getClientHeight() - dialogs.getOffsetHeight()) >> 1;
		dialogs.setPopupPosition(Math.max(Window.getScrollLeft() + left, 0), 10);
	}
	
	public static void hide() {
		dialogs.hide();
	}
}

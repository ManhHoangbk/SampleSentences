package com.koolsoft.samplesentences.client.basic;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollEndEvent.Handler;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;


public class BasicViewImpl implements BasicView {
	
	protected BasicViewImplUiBinder basicUiBinder = GWT.create(BasicViewImplUiBinder.class);
	
	protected interface BasicViewImplUiBinder extends UiBinder<Widget, Layout> {}
	
	protected final Layout layout;
	
	public static class Layout {
		private final BasicViewImpl basicView;
		@UiField
		protected RootFlexPanel mainPanel;
		@UiField
		protected FlowPanel pageContentPanel;
		@UiField 
		protected HeaderPanel headerPanel;
		
		public Layout(BasicViewImpl basicView) {
			this.basicView = basicView;
		}
		
		public RootFlexPanel getMainPanel() {
			return mainPanel;
		}
		
		public BasicViewImpl getBasicView() {
			return basicView;
		}
		
		public FlowPanel getPageContentPanel() {
			return pageContentPanel;
		}

		public HeaderPanel getHeaderPanel() {
			return this.headerPanel;
		}

	}
	
	public BasicViewImpl() {
		
		this.layout = new Layout(this);
		basicUiBinder.createAndBindUi(this.layout);
		this.layout.getPageContentPanel().setHeight((Window.getClientHeight() - 40) + "px");
	}
	
	@Override
	public Widget asWidget() {
		return layout.getMainPanel();
	}
	
	@Override
	public Layout getLayout() {
		return layout;
	}
}

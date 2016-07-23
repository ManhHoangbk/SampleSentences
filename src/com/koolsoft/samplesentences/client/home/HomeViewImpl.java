package com.koolsoft.samplesentences.client.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;

import com.github.gwtbootstrap.client.ui.Form;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollEndEvent.Handler;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.koolsoft.samplesentences.client.ClientUtils;
import com.koolsoft.samplesentences.client.basic.BasicViewImpl;
import com.koolsoft.samplesentences.client.view.KSDialogOverlay;
import com.koolsoft.samplesentences.client.view.LoadingDialog;
import com.koolsoft.samplesentences.shared.Word;
import com.sun.java.swing.plaf.windows.resources.windows;

public class HomeViewImpl extends BasicViewImpl implements HomeView {

	private static HomeViewImplUiBinder uiBinder = GWT.create(HomeViewImplUiBinder.class);

	interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
	}

	List<HomeInputItem> listInputItem = new ArrayList<HomeInputItem>();
	List<HomeItemView> listCheckBoxItem = new ArrayList<HomeItemView>();
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	SuggestBox box = new SuggestBox(oracle);
	@UiField
	VerticalPanel sentencePanel,addedSamples,panelParent;
	@UiField
	Button btnSave, btnAdd;
	@UiField
	HTML notifySearch;
	HTML notifySample=new HTML();
	HTML loader=new HTML();
	com.google.gwt.user.client.ui.Button button = new com.google.gwt.user.client.ui.Button("Deletel Word");
	KSDialogOverlay overlay=new KSDialogOverlay();
	LoadingDialog dialog=new LoadingDialog();
	public HomeViewImpl() {
		
		this.layout.getPageContentPanel().add(uiBinder.createAndBindUi(this));
//		this.layout.getHeaderPanel().add(button);
		box.setStyleName("suggestBox");
		notifySample.setStyleName("notifyAddSample");
		box.getElement().setAttribute("placeholder", "Tìm kiếm tại đây.....");
		notifySearch.setStyleName("notifySearch");
		this.layout.getHeaderPanel().add(box);
		refreshView();
		
	}

	@Override
	public com.google.gwt.user.client.ui.Button getButton() {
		return button;
	}

	@Override
	public void setNotifySearch(String notifySearch) {
		this.notifySearch.setHTML(notifySearch);
	}

	@Override
	public void demo(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			oracle.add(list.get(i));
		}

	}

	@Override
	public void removeFormInput(int index) {
		ClientUtils.log("index null " + index);
		listInputItem.set(index, null);
	}

	@Override
	public Map<String, Word> getListWordSearch(Map<Long, Word> list) {
		Map<String, Word> mapWord = new HashMap<String, Word>();
		for (Long i : list.keySet()) {
			Word word = list.get(i);
			oracle.add(word.getWordName());
			mapWord.put(word.getWordName(), word);
		}
		box.showSuggestionList();
		return mapWord;
	}

	@Override
	public Button getBtnSave() {
		return btnSave;
	}

	@Override
	public Button getBtnAdd() {
		return btnAdd;
	}

	@Override
	public SuggestBox getBox() {
		return box;
	}

	@Override
	public void addFormInPut() {
		this.btnSave.setVisible(true);
		HomeInputItem inputItem = new HomeInputItem();
		sentencePanel.add(inputItem);
		listInputItem.add(inputItem);
		ClientUtils.log("add oke");
		this.layout.getPageContentPanel().getElement().setScrollTop(Window.getClientHeight() - 45);
	}

	@Override
	public List<HomeInputItem> getListInputItem() {
		return listInputItem;
	}

	@Override
	public void showSentences(List<String> list,int option) {
		this.btnSave.setVisible(false);
		sentencePanel.clear();
		HTML html=new HTML("Sample Sentences chưa có");
		html.setStyleName("notifyAddSample");
		if(option==1){
			sentencePanel.add(html);
		}
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				HomeItemView impl = new HomeItemView(list.get(i), i);
				sentencePanel.add(impl);
				listCheckBoxItem.add(impl);
			}
			this.btnSave.setVisible(true);
		}

	}
	
	@Override
	public void addedSamples(List<String> list){
		addedSamples.clear();
		if (list.size() != 0) {
			notifySample.setHTML("Samples Sentences đã có");
			addedSamples.add(notifySample);
			addedSamples.addStyleName("PanelAddSample");
			for (int i = 0; i < list.size(); i++) {
				HomeItemView impl = new HomeItemView(list.get(i), i);
				impl.sample.setHTML(list.get(i));
				impl.rb.setVisible(false);
				addedSamples.add(impl);
			}
		}
		this.layout.getPageContentPanel().getElement().setScrollTop(0);
		sentencePanel.clear();
		listInputItem.clear();
	}
	
	@Override
	public void loader(Boolean status){
		if(status){
			dialog.show();
		}else{
			time.schedule(500);
		}
		
	}
	Timer time=new Timer() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			dialog.hide();
		}
	};
	
	@Override
	public List<HomeItemView> getListCheckBoxItem() {
		return listCheckBoxItem;
	}

	@Override
	public void refreshView() {
		listCheckBoxItem.clear();
		listInputItem.clear();
		this.btnSave.setVisible(false);
		this.btnAdd.setVisible(false);
		addedSamples.clear();
		sentencePanel.clear();
		addedSamples.getElement().setAttribute("boderBottom","");
	}
}

package com.koolsoft.samplesentences.client.home;

import java.util.List;
import java.util.Map;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.koolsoft.samplesentences.client.basic.BasicView;
import com.koolsoft.samplesentences.shared.Word;

public interface HomeView  extends BasicView{

	Map<String, Word> getListWordSearch(Map<Long, Word> list);

	SuggestBox getBox();

	List<HomeItemView> getListCheckBoxItem();

	Button getBtnSave();

	void setNotifySearch(String notifySearch);

	void demo(List<String> list);

	Button getBtnAdd();

	void addFormInPut();

	List<HomeInputItem> getListInputItem();

	void refreshView();

	void removeFormInput(int index);

	com.google.gwt.user.client.ui.Button getButton();

	void addedSamples(List<String> list);

	void showSentences(List<String> list, int option);

	void loader(Boolean status);

	void clearPanelInput();

}

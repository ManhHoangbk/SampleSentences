package com.koolsoft.samplesentences.client.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.koolsoft.samplesentences.client.ClientFactory;
import com.koolsoft.samplesentences.client.ClientUtils;
import com.koolsoft.samplesentences.client.SampleSentences;
import com.koolsoft.samplesentences.client.basic.BasicActivity;
import com.koolsoft.samplesentences.client.getData.GetData;
import com.koolsoft.samplesentences.client.getData.GetObjectJson;
import com.koolsoft.samplesentences.client.view.Toaster;
import com.koolsoft.samplesentences.shared.Word;

public class HomeActivity extends BasicActivity {
	private HomeView view;
	GetObjectJson objectJson = new GetObjectJson();
	Map<String, Word> hashMap = new HashMap<>();
	private String keyWord;

	public HomeActivity(ClientFactory clientFactory, Place place) {
		super(clientFactory, place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getHomeView();
		panel.setWidget(view);
		super.start(panel, eventBus, view);
	}

	@Override
	public void bind() {
		view.getBox().addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {

			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				view.refreshView();
				String word = event.getSelectedItem().getReplacementString();
				keyWord = word;
				ClientUtils.log("word: " + word);
				getListSentencesByWord(word);
			}
		});

		view.getBox().addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeKeyCode() == 13) {
					view.refreshView();
					view.setNotifySearch("");
					String word = view.getBox().getValue();
					if (word.trim().isEmpty()) {
						view.setNotifySearch("Nhập nội dung để tìm kiếm");
					} else {
						ClientUtils.log("aa: " + word);
						fullTextSearch(word);
					}

				}
			}
		});

		addHandlerRegistration(view.getBtnSave().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				bindItemView();
			}
		}));

		addHandlerRegistration(view.getBtnAdd().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				view.addFormInPut();
				bindRemoveFormInputItem();
			}
		}));
		
		addHandlerRegistration(view.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				deletelListWord();
			}
		}));
	}

	@Override
	public void loadData() {
//		deletelFullTS();
//		deletelListWord();
	}

	private void getListWord() {
		SampleSentences.greetingService.getListWords(new AsyncCallback<List<Word>>() {

			@Override
			public void onSuccess(List<Word> result) {
				// TODO Auto-generated method stub
				List<String> list = new ArrayList<>();
				for (int i = 0; i < result.size(); i++) {
					// ClientUtils.log("name: " + result.size());
					list.add(result.get(i).getWordName());
				}
				view.demo(list);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void fullTextSearch(String word) {
		SampleSentences.greetingService.getListSearch(word, new AsyncCallback<Map<Long, Word>>() {

			@Override
			public void onSuccess(Map<Long, Word> result) {
				// TODO Auto-generated method stub
				ClientUtils.log("full text search success");
				if (result.size() != 0) {
					hashMap = view.getListWordSearch(result);
					ClientUtils.log("size: " + result.size());
					view.setNotifySearch("");
				} else {
					view.showSentences(new ArrayList<String>());
					view.setNotifySearch("Từ khóa không có trong dữ liệu");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				ClientUtils.log("full text search failure");
			}
		});
	}

	private void getListSentencesByWord(String word) {
		objectJson.getObjectJsonbyWord(word, new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				// TODO Auto-generated method stub
				ClientUtils.log("size: " + result.size());
				if (result.size() != 0) {
					view.showSentences(result);
					view.setNotifySearch("");

				} else {
					view.setNotifySearch("Không có dữ liệu hiển thị,nhập dữ liệu tại đây");
					addSampleSentences();
					view.showSentences(new ArrayList<String>());
					ClientUtils.log("list sentences null!!!!");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Autosdas-generated method stub

			}
		});
	}

	private void bindRemoveFormInputItem() {
		List<HomeInputItem> listinput = view.getListInputItem();
		int size = listinput.size();
		final int index = size - 1;
		final HomeInputItem inputItem = listinput.get(index);

		inputItem.btnRemove.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				view.removeFormInput(index);
				// inputItem.btnRemove.setVisible(false);
				inputItem.removeFromParent();
				ClientUtils.log("remove: " + index);
			}
		});
	}

	private void addSampleSentences() {
		view.getBtnAdd().setVisible(true);
		view.getBtnSave().setVisible(true);
	}

	public void bindItemView() {
		int i = 0;
		int j = 0;
		JSONObject retObject = new JSONObject();
		JSONArray arrayVar = new JSONArray();
		List<String> listSentences = new ArrayList<String>();

		List<HomeItemView> listCheckBoxItem = new ArrayList<HomeItemView>();
		listCheckBoxItem = view.getListCheckBoxItem();

		List<HomeInputItem> listInputItem = new ArrayList<HomeInputItem>();
		listInputItem = view.getListInputItem();

		if (listCheckBoxItem.size() != 0) {
			boolean statusClick = false;

			ClientUtils.log("i: " + i + " j: " + j);
			for (; i < listCheckBoxItem.size(); i++) {
				boolean check = listCheckBoxItem.get(i).rb.getValue();
				ClientUtils.log("check: " + i + " " + check);
				if (check) {
					statusClick = true;
					String text = listCheckBoxItem.get(i).rb.getText();
					listSentences.add(text);
					arrayVar.set(j++, new JSONString(text));
				}
			}
			retObject.put("sentences", arrayVar);
			if (statusClick) {
				Toaster.showToast("Lưu thành công!!!!");
				updateSentence(retObject.toString());
			} else {
				Toaster.showToast("Bạn chưa chọn câu nào!!!");
			}
		} else if (listInputItem.size() != 0) {
			boolean statusInput = false;
			for (; i < listInputItem.size(); i++) {
				if (listInputItem.get(i) != null) {
					String text = listInputItem.get(i).getSentence().getText();
					if (!text.trim().isEmpty()) {
						listSentences.add(text);
						arrayVar.set(j++, new JSONString(text));
					} else {
						statusInput = true;
					}
				}

			}
			retObject.put("sentences", arrayVar);
			if (statusInput == false) {
				if (j != 0) {
					Toaster.showToast("Lưu thành công!!!!");
					updateSentence(retObject.toString());
				} else {
					Toaster.showToast("Chưa nhập dữ liệu!!!!");
				}

			} else {
				Toaster.showToast("không được để trống ô!!!");
			}
		}

		// ClientUtils.log("xx: " + retObject.toString());

	}

	// convert from json to tring
	private void covertToString() {
		// convert json to string
		// JSONValue jsonValue = JSONParser.parseStrict(retObject.toString());
		// JSONObject object = jsonValue.isObject();
		// ClientUtils.log("size json:
		// "+object.get("sentences").isArray().size());
		// for(int k=0;k<object.get("sentences").isArray().size();k++){
		// String
		// x=object.get("sentences").isArray().get(k).isString().stringValue();
		// ClientUtils.log("text: "+x);
		// }
	}

	private void updateSentence(String sentence) {
		view.getBox().setText("");
		Word word = hashMap.get(keyWord);
		word.setSentence(sentence);
		ClientUtils.log("senten: " + word.getSentence());
		ClientUtils.log("xxx: " + word.getWordName());
		ClientUtils.log("xxx: " + word.getWordId());
		ClientUtils.log("xxx: " + word.getId());
		// SampleSentences.greetingService.updateWord(word, new
		// AsyncCallback<Void>() {
		//
		// @Override
		// public void onSuccess(Void result) {
		// // TODO Auto-generated method stub
		// Toaster.showToast("Lưu thành công!!!");
		// ClientUtils.log("success update");
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// // TODO Auto-generated method stub
		// ClientUtils.log("failure update");
		// }
		// });
	}

	private void deletelListWord() {
		SampleSentences.greetingService.deletelListWord(new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void getObjectWord(String word) {
		SampleSentences.greetingService.getObjectWord(word, new AsyncCallback<Word>() {
			@Override
			public void onSuccess(Word result) {
				// TODO Auto-generated method stub
				ClientUtils.log("a: " + result.getSentence());
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
}
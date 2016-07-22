package com.koolsoft.samplesentences.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.koolsoft.samplesentences.shared.Word;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	void getListWords(AsyncCallback<List<Word>> callback);

	void getListSearch(String word, AsyncCallback<Map<Long, Word>> callback);

	void deletelListWord(AsyncCallback<Void> callback);

	void updateWord(Word word, AsyncCallback<Void> callback);

	void getObjectWord(String word, AsyncCallback<Word> callback);

}

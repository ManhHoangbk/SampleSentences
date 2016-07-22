package com.koolsoft.samplesentences.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.koolsoft.samplesentences.shared.Word;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

	List<Word> getListWords();

	Map<Long, Word> getListSearch(String word);

	void deletelListWord();

	void updateWord(Word word);


	Word getObjectWord(String word);

}

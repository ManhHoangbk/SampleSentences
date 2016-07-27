package com.koolsoft.samplesentences.server;

import com.koolsoft.samplesentences.client.GreetingService;
import com.koolsoft.samplesentences.server.fulltextsearch.Constants;
import com.koolsoft.samplesentences.server.fulltextsearch.SearchIndexManager;
import com.koolsoft.samplesentences.shared.AccentRemover;
import com.koolsoft.samplesentences.shared.Word;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.search.Document.Builder;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.ObjectifyService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	static {
		ObjectifyService.register(Word.class);
	}

	@SuppressWarnings("unchecked")

	@Override
	public void updateWord(Word word) {
		ofy().save().entities(word).now();
	}

	@Override
	public Word getObjectWord(String word) {
		Word word2 = ofy().load().type(Word.class).filter("wordName", word).list().get(0);
		Word word3 = word2;

		return word3;
	}

	@Override
	public Map<Long, Word> getListSearch(String word) {
		Results<ScoredDocument> documents = SearchIndexManager.INSTANCE
				.retrieveDocumentsWord(AccentRemover.removeAccent(word));
		ArrayList<Long> ids = new ArrayList<Long>();
		for (ScoredDocument document : documents) {
			Long id = Long.parseLong(document.getId());
			ids.add(id);
		}

		Map<Long, Word> mapPost = ofy().load().type(Word.class).ids(ids);
		System.out.println("1");
		System.out.println("size: " + mapPost.size());
		if (mapPost.size() == 0) {
			return new HashMap<Long, Word>();
		}
		return new HashMap<Long, Word>(mapPost);

	}

	public int upListWord(List<Word> listWords) {
		// for (int i = 0; i < listWords.size(); i++) {
		// Word word = listWords.get(i);
		// Long id = ofy().save().entity(word).now().getId();
		// word.setId(id);
		// addFullTextSearchIndexWord(word);
		// }
		ofy().save().entities(listWords).now();
		return listWords.size();
	}

	public void addFTSListWord() {

		List<Word> list = ofy().load().type(Word.class).list();
		System.out.print("size: " + list.size());
		if (list.size() > 2000) {
			for (int i = 2000; i >= 0; i--) {
				addFullTextSearchIndexWord(list.get(i));
			}
			for (int i = 2000; i < list.size(); i++) {
				addFullTextSearchIndexWord(list.get(i));
			}
		} else {
			for (int i = list.size() - 1; i >= 0; i--) {
				addFullTextSearchIndexWord(list.get(i));
			}
		}
	}

	public void newListSample() {
		List<Word> list = ofy().load().type(Word.class).list();
		System.out.print("size: " + list.size());
		for (int i = list.size() - 1; i >= 0; i--) {
			Word word = list.get(i);
			word.setSentence(null);
			ofy().save().entity(word);
		}
	}

	public void addFullTextSearchIndexWord(Word word) {
		String id = String.valueOf(word.getId());

		Document document = SearchIndexManager.INSTANCE.retrieveDocumentWord(id + "");
		if (document != null) {
			SearchIndexManager.INSTANCE.deleteDocument(id + "");
		}
		Builder builder = Document.newBuilder().setId(id + "").addField(Field.newBuilder().setName("wordName")
				.setText(AccentRemover.removeAccent(word.getWordName().toLowerCase())));
		SearchIndexManager.INSTANCE.indexDocument(Constants.WORD_INDEX, builder.build());
	}

	@Override
	public void deletelListWord() {
		List<Word> list = ofy().load().type(Word.class).list();
		ofy().delete().entities(list).now();
	}

	@Override
	public List<Word> getListWords() {
		List<Word> list = ofy().load().type(Word.class).list();
		if (list == null) {
			list = new ArrayList<Word>();
		}

		return new ArrayList<Word>(list);
	}

	// public void deletelFullTextSearch() {
	// List<Word> list = ofy().load().type(Word.class).list();
	// System.out.print("list.size: "+list.size());
	// for (int i = list.size()-1; i >0; i--) {
	// String id = String.valueOf(list.get(i).getId());
	// System.out.println(""+i);
	// IndexSpec indexSpec =
	// IndexSpec.newBuilder().setName(Constants.WORD_INDEX).build();
	// Index index =
	// SearchServiceFactory.getSearchService().getIndex(indexSpec);
	// // Retrieve the Record from the Index
	// index.delete(id);
	// }
	//
	// }

}

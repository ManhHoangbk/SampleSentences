package com.koolsoft.samplesentences.client.getData;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.koolsoft.samplesentences.client.ClientUtils;

public class GetObjectJson {

	public GetObjectJson() {

	}

	public void getObjectJsonbyWord(String word, final AsyncCallback<List<String>> callback) {
		String postUrl = "http://api.wordnik.com/";
		String requestData = "v4/word.json/" + word
				+ "/examples?includeDuplicates=false&useCanonical=true&skip=0&limit=30&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5";
		String url = postUrl + requestData;
		ClientUtils.log("url: "+url);
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			builder.sendRequest(url, new RequestCallback() {
				public void onError(Request request, Throwable e) {
					ClientUtils.log("failure: " + e.getMessage());
				}

				public void onResponseReceived(Request request, Response response) {
					if (200 == response.getStatusCode()) {
						JSONValue jsonValue = JSONParser.parseStrict(response.getText());
						JSONObject object = jsonValue.isObject();
//						ClientUtils.log("aa: "+object.toString());
						if(object.size()==1){
							callback.onSuccess(getSentenceByWord(object));
						}else{
							callback.onSuccess(new ArrayList<String>());
						}
						
					}

				}
			});
		} catch (RequestException e) {
			ClientUtils.log("falure: " + e.getMessage());
		}

	}

	private List<String> getSentenceByWord(JSONObject json) {
		List<String> listSentence = new ArrayList<String>();
		int size=json.get("examples").isArray().size();
//		ClientUtils.log("size json: "+size);
		for (int i = 0; i < json.get("examples").isArray().size(); i++) {
			String content = json.get("examples").isArray().get(i).isObject().get("text").isString().stringValue();
			listSentence.add(content);
		}
		
		return listSentence;
	}	

}

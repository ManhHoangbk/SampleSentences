package com.koolsoft.samplesentences.client.basic;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class BasicPlace extends Place{
	protected String token = "";
	public BasicPlace(){};
	
	public String getToken(){
		return this.token;
	}
	
	public static class Tokenizer implements PlaceTokenizer<BasicPlace> {
		@Override
		public BasicPlace getPlace(String token) {
			return new BasicPlace();
		}
		
		@Override
		public String getToken(BasicPlace place) {
			return place.getToken();
		}
		
	}
	
}

package com.koolsoft.samplesentences.client.home;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.koolsoft.samplesentences.client.basic.BasicPlace;

public class HomePlace extends BasicPlace {

	public HomePlace() {

	}

	public static class Tokenizer implements PlaceTokenizer<HomePlace> {

		@Override
		public HomePlace getPlace(String token) {
			return new HomePlace();
		}

		@Override
		public String getToken(HomePlace place) {
			return place.getToken();
		}

	}
}

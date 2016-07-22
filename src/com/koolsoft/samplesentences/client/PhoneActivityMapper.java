package com.koolsoft.samplesentences.client;

import org.eclipse.jdt.core.compiler.IScanner;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.koolsoft.samplesentences.client.home.HomeActivity;
import com.koolsoft.samplesentences.client.home.HomePlace;

public class PhoneActivityMapper implements ActivityMapper{
	private ClientFactory clientFactory;
	
	public PhoneActivityMapper(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if(place instanceof HomePlace){
			return new HomeActivity(clientFactory, place);
		}
//		if(place instanceof ApplicationPlace){
//			return new ApplicationActivity(clientFactory, place);
//		}
		return null;
	}
}

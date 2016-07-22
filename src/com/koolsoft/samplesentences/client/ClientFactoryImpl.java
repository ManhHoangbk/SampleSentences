package com.koolsoft.samplesentences.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.koolsoft.samplesentences.client.home.HomePlace;
import com.koolsoft.samplesentences.client.home.HomeView;
import com.koolsoft.samplesentences.client.home.HomeViewImpl;

public  class ClientFactoryImpl implements ClientFactory {
	private SimpleEventBus eventBus;
	private PlaceController placeController;
	private HomeView homeView;
	
	public ClientFactoryImpl (){
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
	}
	
	@Override
	public PlaceController getPlaceController(){
		return placeController;
	}
	
	public EventBus getEventBus(){
		return eventBus;
	}

	@Override
	public HomeView getHomeView() {
		if(homeView==null){
			homeView=new HomeViewImpl();
		}
		return homeView;
	}
	
}

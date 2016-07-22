package com.koolsoft.samplesentences.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.koolsoft.samplesentences.client.home.HomeView;

public interface ClientFactory {

	PlaceController getPlaceController();
	EventBus getEventBus();
	HomeView getHomeView();
}

package com.koolsoft.samplesentences.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface BHClientBundleBaseTheme extends ClientBundle, BHClientBundle {
	public static final BHClientBundleBaseThemeImpl IMPL = new BHClientBundleBaseThemeImpl();
	
	@Override
	@Source("json/language16.json")
	TextResource getJson();

	
}
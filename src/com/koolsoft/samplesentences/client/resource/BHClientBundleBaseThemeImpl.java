package com.koolsoft.samplesentences.client.resource;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.MGWT;

public class BHClientBundleBaseThemeImpl implements BHTheme {
	
    private BHClientBundle bundle;

    public BHClientBundleBaseThemeImpl() {

    		//bundle = GWT.create(BHClientBundleBaseThemeRetina.class);
    	 	bundle = GWT.create(BHClientBundleBaseThemeRetina.class);
    		if (MGWT.getOsDetection().isAndroid()) {
                if (MGWT.getOsDetection().isAndroidPhone()) {
                    bundle = GWT.create(BHClientBundleBaseThemeAndroid.class);
                }

	            if (MGWT.getOsDetection().isTablet() || MGWT.getOsDetection().isAndroidTablet()) {
	                    bundle = GWT.create(BHClientBundleBaseThemeAndroidTablet.class);
	            }    			
    		}
    		else if (MGWT.getOsDetection().isIOs()) {
    			if (MGWT.getOsDetection().isIPadRetina() || MGWT.getOsDetection().isRetina()) {
    				 bundle = GWT.create(BHClientBundleBaseThemeRetina.class);
    			}
    			else if (MGWT.getOsDetection().isIPad()) {
                    bundle = GWT.create(BHClientBundleBaseThemeIPad.class);    				
	            } else {
	                bundle = GWT.create(BHClientBundleBaseThemeIPhone.class);
	            }
    		}
    }	
    
    @Override
    public BHClientBundle getBHMGWTClientBundle() {
            return bundle;
    }
    
}
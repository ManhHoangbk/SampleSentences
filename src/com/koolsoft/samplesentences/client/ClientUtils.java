package com.koolsoft.samplesentences.client;

import com.google.gwt.json.client.JSONObject;

public class ClientUtils {
	public static native void log(String msg) /*-{
		$wnd.console.log(msg);
	}-*/;
	
	public static String getStringValue(String key, JSONObject object) {
		String s = "";
		if (object.containsKey(key) && object.get(key) != null)
			try {
				s = object.get(key).isString().stringValue();
			} catch (Exception e) {
			}
		if (s.equalsIgnoreCase("null"))
			s = "";
		return s;
	}
}

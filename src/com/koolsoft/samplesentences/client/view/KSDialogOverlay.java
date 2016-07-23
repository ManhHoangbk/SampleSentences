/**
 * 
 */
package com.koolsoft.samplesentences.client.view;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.PopinDialogOverlay;

/**
 * @author hungzombxd
 *
 */
public class KSDialogOverlay extends PopinDialogOverlay{
	/**
	 * 
	 */
	public static KSDialogOverlay dialogOverlay = null;
	public KSDialogOverlay() {
		super();
		setCenterContent(true);
	}
	
	public static boolean hideAll() {
		if (KSDialogOverlay.dialogOverlay != null) {
			KSDialogOverlay.dialogOverlay.hide();
			return true;
		}
		return false;
	}
	
	@Override
	public void show() {
		show(true);
	}
	
	public void show(final boolean hideAllDialog) {
		try {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				
				@Override
				public void execute() {
					if(hideAllDialog)
						hideAll();
					showDialog();
				}
			});
		} catch (Exception e) {
		}
	}
	
	public void show(Widget widget) {
		clear();
		add(widget);
		show();
	}
	
	public void showDialog() {
		KSDialogOverlay.dialogOverlay = this;
		super.show();
	}
	
	@Override
	public void hide() {
		super.hide();
		KSDialogOverlay.dialogOverlay = null;
	}
	
	@Override
	protected Animation getHideAnimation() {
		return null;
	}
	
	@Override
	protected Animation getShowAnimation() {
		return null;
	}
}

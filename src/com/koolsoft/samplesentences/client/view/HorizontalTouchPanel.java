package com.koolsoft.samplesentences.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.recognizer.longtap.HasLongTapHandlers;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapEvent;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapHandler;
import com.googlecode.mgwt.dom.client.recognizer.pinch.HasPinchHandlers;
import com.googlecode.mgwt.dom.client.recognizer.pinch.PinchEvent;
import com.googlecode.mgwt.dom.client.recognizer.pinch.PinchHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.HasSwipeHandlers;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeMoveEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeMoveHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeStartEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeStartHandler;
import com.googlecode.mgwt.ui.client.widget.touch.GestureUtility;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl;


public class HorizontalTouchPanel extends HorizontalPanel implements HasTouchHandlers, HasTapHandlers, 
							HasPinchHandlers, HasSwipeHandlers, HasLongTapHandlers {

	private static final TouchWidgetImpl impl = GWT.create(TouchWidgetImpl.class);
	protected final GestureUtility gestureUtility;
	
	public HorizontalTouchPanel() {
		super();
		gestureUtility = new GestureUtility(this);
	}

    @Override
    public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
            return impl.addTouchStartHandler(this, handler);

    }

    @Override
    public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
            return impl.addTouchMoveHandler(this, handler);

    }

    @Override
    public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
            return impl.addTouchCancelHandler(this, handler);

    }

    @Override
    public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
            return impl.addTouchEndHandler(this, handler);

    }

    @Override
    public HandlerRegistration addTouchHandler(TouchHandler handler) {
            HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

            handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(handler));
            handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(handler));
            handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(handler));
            handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(handler));
            return handlerRegistrationCollection;
    }

    @Override
    public HandlerRegistration addTapHandler(TapHandler handler) {
            gestureUtility.ensureTapRecognizer();
            return addHandler(handler, TapEvent.getType());
    }

    public HandlerRegistration addSwipeStartHandler(SwipeStartHandler handler) {
            gestureUtility.ensureSwipeRecognizer();
            return addHandler(handler, SwipeStartEvent.getType());
    }

    public HandlerRegistration addSwipeMoveHandler(SwipeMoveHandler handler) {
            gestureUtility.ensureSwipeRecognizer();
            return addHandler(handler, SwipeMoveEvent.getType());
    }

    public HandlerRegistration addSwipeEndHandler(SwipeEndHandler handler) {
            gestureUtility.ensureSwipeRecognizer();
            return addHandler(handler, SwipeEndEvent.getType());
    }

    @Override
    public HandlerRegistration addPinchHandler(PinchHandler handler) {
            gestureUtility.ensurePinchRecognizer(this);
            return addHandler(handler, PinchEvent.getType());
    }

    @Override
    public HandlerRegistration addLongTapHandler(LongTapHandler handler) {
            gestureUtility.ensureLongTapHandler();
            return addHandler(handler, LongTapEvent.getType());
    }	
}
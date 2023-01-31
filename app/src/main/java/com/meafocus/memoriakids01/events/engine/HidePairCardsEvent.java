package com.meafocus.memoriakids01.events.engine;

import com.meafocus.memoriakids01.events.AbstractEvent;
import com.meafocus.memoriakids01.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class HidePairCardsEvent extends AbstractEvent {

	public static final String TYPE = HidePairCardsEvent.class.getName();
	public int id1;
	public int id2;

	public HidePairCardsEvent(int id1, int id2) {
		this.id1 = id1;
		this.id2 = id2;
	}

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}

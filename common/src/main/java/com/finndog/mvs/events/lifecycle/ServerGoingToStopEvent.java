package com.finndog.mvs.events.lifecycle;


import com.finndog.mvs.events.base.EventHandler;

public class ServerGoingToStopEvent {

    public static final ServerGoingToStopEvent INSTANCE = new ServerGoingToStopEvent();

    public static final EventHandler<ServerGoingToStopEvent> EVENT = new EventHandler<>();

    private ServerGoingToStopEvent() {}
}

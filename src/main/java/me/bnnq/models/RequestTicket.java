package me.bnnq.models;

import java.time.LocalDateTime;

public class RequestTicket
{
    private final Request request;
    private final LocalDateTime dateTime;

    public RequestTicket(Request request, LocalDateTime dateTime)
    {
        this.request = request;
        this.dateTime = dateTime;
    }

    public Request getRequest()
    {
        return request;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

}

package me.bnnq.models;

import me.bnnq.Server;

public class Client
{
    private String name;
    private int priority;

    public Client(String name, int priority)
    {
        this.name = name;
        this.priority = priority;
    }

    public void sendRequest(String content, Server server)
    {
        Request request = new Request(content, this);
        server.acceptRequest(request);
    }

    public String getName()
    {
        return name;
    }

    public int getPriority()
    {
        return priority;
    }

}

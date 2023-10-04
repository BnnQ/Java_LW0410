package me.bnnq.models;

public class Request implements Comparable<Request>
{
    private final String content;
    private final Client client;

    public Request(String content, Client client)
    {
        this.content = content;
        this.client = client;
    }

    @Override
    public int compareTo(Request o)
    {
        return o.getClient().getPriority() - client.getPriority();
    }

    public String getContent()
    {
        return content;
    }

    public Client getClient()
    {
        return client;
    }

}

package me.bnnq;

import me.bnnq.abstractions.IDisposable;
import me.bnnq.models.Request;
import me.bnnq.models.RequestTicket;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server implements IDisposable
{
    private final PriorityQueue<Request> requests;
    private final Queue<RequestTicket> requestHistory;
    private final Queue<RequestTicket> requestProcessingHistory;
    private final ScheduledExecutorService requestHandlerScheduler;

    public Server(int secondsToProcessRequest)
    {
        requests = new PriorityQueue<>();
        requestHistory = new LinkedList<>();
        requestProcessingHistory = new LinkedList<>();

        requestHandlerScheduler = Executors.newScheduledThreadPool(1);
        requestHandlerScheduler.scheduleAtFixedRate(this::processRequest, 0, secondsToProcessRequest, TimeUnit.SECONDS);
    }

    public void acceptRequest(Request request)
    {
        requests.add(request);
        requestHistory.add(new RequestTicket(request, LocalDateTime.now()));
    }

    private void processRequest()
    {
        Request request = requests.poll();
        if (request != null)
        {
            System.out.printf("Request \"%s\" processed\n", request.getContent());
            requestProcessingHistory.add(new RequestTicket(request, LocalDateTime.now()));
        }

    }

    public void printRequestHistory()
    {
        System.out.println("Request history:");
        for (RequestTicket requestTicket : requestHistory)
        {
            Request request = requestTicket.getRequest();
            System.out.printf("Request \"%s\" from client \"%s\" accepted at %s\n", request.getContent(), request.getClient().getName(), requestTicket.getDateTime());
        }
    }

    public void printRequestProcessingHistory()
    {
        System.out.println("Request processing history:");
        for (RequestTicket requestTicket : requestProcessingHistory)
        {
            Request request = requestTicket.getRequest();
            System.out.printf("Request \"%s\" from client \"%s\" processed at %s\n", request.getContent(), request.getClient().getName(), requestTicket.getDateTime());
        }
    }

    public void dispose()
    {
        requestHandlerScheduler.shutdown();
        try {
            if (!requestHandlerScheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                requestHandlerScheduler.shutdownNow();
            }
        } catch (InterruptedException ex) {
            requestHandlerScheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
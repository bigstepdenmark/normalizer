package router;

import message.Receiver;

public class Router
{
    private String queueName;
    private Receiver receiver;

    public Router( String queueName )
    {
        this.queueName = queueName;
        receiver = new Receiver( queueName, new RouterWorker() );
    }

    public void startReceiver()
    {
        receiver.handleDelivery();
    }

    public String getQueueName()
    {
        return queueName;
    }

    public void setQueueName( String queueName )
    {
        this.queueName = queueName;
    }

    public Receiver getReceiver()
    {
        return receiver;
    }

    public void setReceiver( Receiver receiver )
    {
        this.receiver = receiver;
    }
}

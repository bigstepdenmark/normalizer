package message;

import com.rabbitmq.client.*;
import interfaces.Worker;

import java.io.IOException;

public class Receiver
{
    private String queueName;
    private MessageConnection mc;
    private Worker worker;


    public Receiver( String queueName, Worker worker )
    {
        this.queueName = queueName;
        this.worker = worker;
        mc = new MessageConnection( "datdb.cphbusiness.dk", "guest" );
    }

    public boolean close()
    {
        return mc.close();
    }

    /**
     * Handle delivered messages
     */
    public void handleDelivery()
    {
        try
        {
            mc.getChannel().queueDeclare( queueName, false, false, false, null );
            System.out.println( "\nWaiting for messages. To exit press CTRL+C" );
            System.out.println( "====================================================" );

            Consumer consumer = new DefaultConsumer( mc.getChannel() )
            {
                int counter = 0;

                @Override
                public void handleDelivery( String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body ) throws IOException
                {
                    try
                    {
                        String message = new String( body, "UTF-8" );
                        System.out.println( "[Received] --> '" + message + "'" );
                        worker.doWork( message );
                    }
                    catch( IOException ex )
                    {
                        ex.printStackTrace();
                    }
                }
            };
            mc.getChannel().basicConsume( queueName, true, consumer );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }
}

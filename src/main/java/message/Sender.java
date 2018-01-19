package message;

import com.rabbitmq.client.BuiltinExchangeType;

import java.io.IOException;
import java.util.List;

public class Sender
{
    private String queueName;
    private String exchangeName;
    private MessageConnection mc;
    private boolean isQueueDeclared = false;

    public Sender()
    {
        mc = new MessageConnection( "datdb.cphbusiness.dk", "guest" );
    }

    public Sender( String queueName )
    {
        this.queueName = queueName;
        mc = new MessageConnection( "datdb.cphbusiness.dk", "guest" );
    }

    /**
     * Send message
     *
     * @param message String
     */
    public void sendMessage( String message )
    {
        String response = "Message could not be sent!";

        try
        {
            response = basicPublish( message );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        System.out.println( response );
    }

    /**
     * Send message
     *
     * @param message String
     */
    public void sendMessage( String message, String queueName )
    {
        String response = "Message could not be sent!";

        try
        {
            response = basicPublish( message, queueName );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        System.out.println( response );
    }

    public boolean close()
    {
        return mc.close();
    }

    /**
     * Declare queue and publish message to channel
     *
     * @param message String
     * @return String
     * @throws IOException
     */
    private String basicPublish( String message ) throws IOException
    {
        if( !isQueueDeclared )
        {
            mc.getChannel().queueDeclare( queueName, false, false, false, null );
            isQueueDeclared = true;
        }

        mc.getChannel().basicPublish( "", queueName, null, message.getBytes() );

        return "[Sent] --> '" + message + "'";
    }

    /**
     * Declare queue and publish message to channel
     *
     * @param message String
     * @return String
     * @throws IOException
     */
    private String basicPublish( String message, String queueName ) throws IOException
    {
        if( !isQueueDeclared )
        {
            mc.getChannel().queueDeclare( queueName, false, false, false, null );
            isQueueDeclared = true;
        }

        mc.getChannel().basicPublish( "", queueName, null, message.getBytes() );

        return "[Sent] --> '" + message + "'";
    }

    private void basicPublish( String message, String exchangeName, List<String> queueNames )
    {
        try
        {
            mc.getChannel().exchangeDeclare( exchangeName, BuiltinExchangeType.FANOUT );
            bindQueues( exchangeName, queueNames );
            mc.getChannel().basicPublish( exchangeName, "", null, message.getBytes() );
            System.out.println( "[Sent] --> '" + message + "' to " + queueNames.toString() );
        }
        catch( IOException e )
        {
            System.out.println( "[Error] --> '" + message + "' could not be sent to " + queueNames.toString() );
        }
    }

    private void bindQueues( String exchangeName, List<String> queueNames ) throws IOException
    {
        for( String queueName : queueNames )
        {
            mc.getChannel().queueBind( queueName, exchangeName, "" );
        }
    }
}

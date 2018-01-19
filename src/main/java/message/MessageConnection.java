package message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageConnection
{
    private String hostname; // Local: localhost, Host: datdb.cphbusiness.dk
    private String username; // Local: root, port=5672, Host: guest/cph, password=cph, port=15672:
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public MessageConnection( String hostname, String username )
    {
        this.hostname = hostname;
        this.username = username;
        connect();
    }

    /**
     * Create Factory, connection and channel
     *
     * @return boolean
     */
    public boolean connect()
    {
        try
        {
            return createFactory() && newConnection() && createChannel();
        }
        catch( IOException | TimeoutException e )
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Close channel and connection
     *
     * @return boolean
     */
    public boolean close()
    {
        try
        {
            channel.close();
            connection.close();

            return true;
        }
        catch( IOException | TimeoutException e )
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Create and set Factory properties
     *
     * @return boolean
     */
    private boolean createFactory()
    {
        if( factory == null )
            factory = new ConnectionFactory();

        factory.setHost( hostname );
        factory.setUsername( username );

        return factory.getHost().equals( hostname );
    }

    /**
     * Create Connection
     *
     * @return boolean
     * @throws IOException
     * @throws TimeoutException
     */
    private boolean newConnection() throws IOException, TimeoutException
    {
        if( connection == null )
            connection = factory.newConnection();

        return connection.isOpen();
    }

    /**
     * Create Channel
     *
     * @return boolean
     * @throws IOException
     * @throws TimeoutException
     */
    private boolean createChannel() throws IOException, TimeoutException
    {
        if( channel == null )
            channel = connection.createChannel();

        return channel.isOpen();
    }

    //-------------------------------------------------------------
    // Getters & Setters
    //-------------------------------------------------------------
    public String getHostname()
    {
        return hostname;
    }

    public void setHostname( String hostname )
    {
        this.hostname = hostname;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public ConnectionFactory getFactory()
    {
        return factory;
    }

    public void setFactory( ConnectionFactory factory )
    {
        this.factory = factory;
    }

    public Connection getConnection()
    {
        return connection;
    }

    public void setConnection( Connection connection )
    {
        this.connection = connection;
    }

    public Channel getChannel()
    {
        return channel;
    }

    public void setChannel( Channel channel )
    {
        this.channel = channel;
    }
}

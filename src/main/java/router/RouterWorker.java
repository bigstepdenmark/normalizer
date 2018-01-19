package router;

import entities.Response;
import interfaces.Worker;
import mains.Main;
import message.Receiver;
import message.Sender;
import translators.json.JsonTranslator;
import translators.json.JsonWorker;
import translators.xml.XmlTranslator;
import translators.xml.XmlWorker;
import validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class RouterWorker implements Worker
{

    private Sender sender;

    public RouterWorker()
    {
        sender = new Sender();

        // Start Translator receivers
        new Receiver( Main.queueNames.get( 1 ), new JsonWorker() ).handleDelivery(); // jsonTranslator receiver
        new Receiver( Main.queueNames.get( 2 ), new XmlWorker() ).handleDelivery(); // xmlTranslator receiver
    }

    @Override
    public void doWork( String... args )
    {
        Validator validator = new Validator( args[ 0 ] );

        if( validator.isJson() )
        {
            // Send raw message to JsonTranslator by rabbitmq
            System.out.println( "Router JSON" );
            sender.sendMessage( args[ 0 ], Main.queueNames.get( 1 ) );
        }
        else if( validator.isXml() )
        {
            // Send raw message to XmlTranslator by rabbitmq
            System.out.println( "Router XML" );
            sender.sendMessage( args[ 0 ], Main.queueNames.get( 2 ) );
        }
        else
        {
            System.out.println( "The format of message is not supported!" );
        }
    }
}

package mains;

import message.Sender;
import router.Router;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static List<String> queueNames = new ArrayList<>();

    public static void main( String[] args )
    {
        /*
        * Bank ---> Router ---> Translator ---> Aggregator
        * */

        // Queue names
        queueNames.add( "group3.bank.normalizer" ); // bank sender --> normalizer/router receiver
        queueNames.add( "group3.normalizer.router.jsontranslator" ); // router sender --> jsontranslator receiver
        queueNames.add( "group3.normalizer.router.xmltranslator" ); // router sender --> xmltranslator receiver
        queueNames.add( "group3.normalizer.normalizer.aggregator" ); // Translator sender --> aggregator receiver

        // Bank simulator
        Sender sender = new Sender( queueNames.get( 0 ) );
        sender.sendMessage( "{\"interestRate\":5.561000000000000,\"ssn\":1605789787}" );

        // Receiver is now ready to receive message from banks
        Router router = new Router( queueNames.get( 0 ) );
        router.startReceiver();
    }
}

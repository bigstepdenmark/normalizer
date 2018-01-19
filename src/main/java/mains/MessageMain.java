package mains;

import message.Receiver;
import message.Sender;
import router.Router;
import router.RouterWorker;

import java.io.IOException;

public class MessageMain
{

    public MessageMain()
    {
    }

    public static void main( String[] args ) throws IOException
    {
        Router router = new Router( "group3.testmex" );
        router.startReceiver();

        Sender sender = new Sender( "group3.testmex" );
        sender.sendMessage( "{\"interestRate\":5.561000000000000,\"ssn\":1605789787}" );
        //Thread.sleep( 1000 );
        sender.sendMessage( "<LoanResponse><interestRate>4.5600000000000005</interestRate><ssn>1098789878</ssn></LoanResponse>" );
        sender.sendMessage( "Invalid format" );
    }
}

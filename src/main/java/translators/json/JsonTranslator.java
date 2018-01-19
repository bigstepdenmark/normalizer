package translators.json;

import entities.Response;
import interfaces.Translator;
import mains.Main;
import message.Receiver;
import message.Sender;
import org.json.JSONObject;

public class JsonTranslator implements Translator
{
    private String message;
    private Sender sender;
    private Receiver receiver;

    public JsonTranslator( String receiverQueueName, String senderQueueName, String message )
    {
        this.message = message;
        sender = new Sender( Main.queueNames.get( 0 ) );
        receiver = new Receiver( receiverQueueName, new JsonWorker() );
    }

    /**
     * Translate String response to Response object.
     *
     * @return Response
     */
    public Response translate()
    {
        JSONObject jsonResponse = new JSONObject( message );
        return new Response( jsonResponse.getDouble( "interestRate" ), jsonResponse.get( "ssn" ).toString() );
    }

    public void receive()
    {
        receiver.handleDelivery();
    }

    public void send()
    {
        sender.sendMessage( translate().toString() );
    }
}

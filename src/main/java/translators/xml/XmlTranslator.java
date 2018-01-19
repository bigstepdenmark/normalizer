package translators.xml;

import entities.Response;
import interfaces.Translator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class XmlTranslator implements Translator
{
    private String bankResponse;

    public XmlTranslator( String bankResponse )
    {
        this.bankResponse = bankResponse;
    }

    /**
     * Translate String response to Response object.
     *
     * @return Response
     */
    public Response translate()
    {
        Document document = Jsoup.parse( bankResponse, "", Parser.xmlParser() );
        Elements interestRate = document.select( "interestRate" );
        Elements ssn = document.select( "ssn" );

        return new Response( interestRate.text() + "," + ssn.text() );
    }

    public void receive()
    {

    }

    public void send()
    {

    }
}

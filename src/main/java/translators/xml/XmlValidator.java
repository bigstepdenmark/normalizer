package translators.xml;

import interfaces.Validator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import validators.ValueValidator;

public class XmlValidator implements Validator
{
    private Document xmlDocument;
    private String message;
    private ValueValidator valueValidator;
    private static final String INTERESTRATE_KEY = "interestRate";
    private static final String SSN_KEY = "ssn";

    public XmlValidator( String message )
    {
        this.message = message;
        this.valueValidator = new ValueValidator();
    }

    public boolean validate()
    {
        return isParsed() && has( INTERESTRATE_KEY, SSN_KEY ) && validateValues();
    }

    public boolean isParsed()
    {
        try
        {
            xmlDocument = Jsoup.parse( message, "", Parser.xmlParser() );
            return xmlDocument.select( "LoanResponse" ).size() > 0;
        }
        catch( Exception ex )
        {
            return false;
        }
    }

    public boolean has( String... keys )
    {
        for( String key : keys )
        {
            if( !( hasKey( key ) && hasValue( key ) ) )
                return false;
        }

        return true;
    }

    public boolean hasValue( String key )
    {
        return xmlDocument.select( key ).hasText();
    }

    public boolean hasKey( String key )
    {
        return xmlDocument.select( key ).size() > 0;
    }

    public String getValue( String key )
    {
        return xmlDocument.select( key ).text();
    }

    public boolean validateValues()
    {
        return valueValidator.validateInterestRate( getValue( INTERESTRATE_KEY ) ) &&
                valueValidator.validateSSN( getValue( SSN_KEY ) );
    }
}

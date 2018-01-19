package translators.json;

import interfaces.Validator;
import org.json.JSONException;
import org.json.JSONObject;
import validators.ValueValidator;

public class JsonValidator implements Validator
{
    private JSONObject jsonObject;
    private ValueValidator valueValidator;
    private String message;
    private static final String INTERESTRATE_KEY = "interestRate";
    private static final String SSN_KEY = "ssn";

    public JsonValidator( String message )
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
            jsonObject = new JSONObject( message );
            return true;
        }
        catch( JSONException ex )
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

    public boolean hasKey( String key )
    {
        return jsonObject.has( key );
    }

    public boolean hasValue( String key )
    {
        return !jsonObject.isNull( key );
    }

    public String getValue( String key )
    {
        return jsonObject.get( key ).toString();
    }

    public boolean validateValues()
    {
        return valueValidator.validateInterestRate( getValue( INTERESTRATE_KEY ) ) &&
                valueValidator.validateSSN( getValue( SSN_KEY ) );
    }
}

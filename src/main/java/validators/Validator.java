package validators;

import translators.json.JsonValidator;
import translators.xml.XmlValidator;

public class Validator
{
    private JsonValidator jsonValidator;
    private XmlValidator xmlValidator;

    public Validator( String message )
    {
        initValidators( message );
    }

    private void initValidators( String message )
    {
        jsonValidator = new JsonValidator( message );
        xmlValidator = new XmlValidator( message );
    }

    public boolean isJson()
    {
        return jsonValidator.validate();
    }

    public boolean isXml()
    {
        return xmlValidator.validate();
    }

    public JsonValidator getJsonValidator()
    {
        return jsonValidator;
    }

    public XmlValidator getXmlValidator()
    {
        return xmlValidator;
    }
}

package validators;

import org.apache.commons.lang3.math.NumberUtils;

public class ValueValidator
{
    public boolean validateInterestRate( String value )
    {
        return NumberUtils.toDouble( value ) >= 0;
    }

    public boolean validateSSN( String value )
    {
        return NumberUtils.isDigits( value ) && value.length() == 10;
    }
}

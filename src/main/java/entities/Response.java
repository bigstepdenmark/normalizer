package entities;

import org.apache.commons.lang3.math.NumberUtils;

public class Response
{
    private double interestRate;
    private String ssn;

    public Response()
    {
    }

    public Response( double interestRate, String ssn )
    {
        this.interestRate = interestRate;
        this.ssn = ssn;
    }

    public Response( String commaSeparatedValues )
    {
        String[] values = commaSeparatedValues.split( "," );
        this.interestRate = NumberUtils.toDouble( values[ 0 ] );
        this.ssn = values[ 1 ];
    }

    public double getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate( double interestRate )
    {
        this.interestRate = interestRate;
    }

    public String getSsn()
    {
        return ssn;
    }

    public void setSsn( String ssn )
    {
        this.ssn = ssn;
    }

    @Override
    public String toString()
    {
        return interestRate + "," + ssn;
    }
}

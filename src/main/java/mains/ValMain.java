package mains;

import validators.Validator;

public class ValMain
{
    public static void main( String[] args )
    {
        // "{\"interestRate\":5.561000000000000,\"ssn\":1605789787}"
        // "<LoanResponse><interestRate>4.5600000000000005</interestRate><ssn>1098789878</ssn></LoanResponse>"
        Validator validator = new Validator( "{\"interestRate\":5.561000000000000,\"ssn\":1605789787}" );

        System.out.println( validator.isJson() );
        // System.out.println( validator.isXml().validate() );
    }
}

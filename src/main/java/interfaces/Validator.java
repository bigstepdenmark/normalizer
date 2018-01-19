package interfaces;

public interface Validator
{
    boolean validate();
    boolean isParsed();
    boolean has( String... keys );
    boolean hasKey( String key );
    boolean hasValue( String key );
    String getValue( String key );
    boolean validateValues();
}

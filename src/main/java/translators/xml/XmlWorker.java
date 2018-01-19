package translators.xml;

import interfaces.Worker;

public class XmlWorker implements Worker
{
    @Override
    public void doWork( String... args )
    {
        System.out.println( "XmlWorker: " + args[ 0 ] );
    }
}

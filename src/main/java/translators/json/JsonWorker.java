package translators.json;

import interfaces.Worker;

public class JsonWorker implements Worker
{


    @Override
    public void doWork( String... args )
    {
        System.out.println( "JsonWorker: " + args[ 0 ] );
    }
}

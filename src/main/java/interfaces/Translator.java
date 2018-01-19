package interfaces;

import entities.Response;

public interface Translator
{
    Response translate();
    void receive();
    void send();
}

package ao;

import org.springframework.context.annotation.Configuration;

/**
 * @author ISJINHAO
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        return "[ECHO] " + message;
    }

}

package ao.proxy.astatic;

import ao.DefaultEchoService;
import ao.EchoService;

public class StaticProxyDemo {

    public static void main(String[] args) {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello,World");
    }
}

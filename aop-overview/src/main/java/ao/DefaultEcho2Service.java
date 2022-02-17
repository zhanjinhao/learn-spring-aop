package ao;

/**
 * @Author ISJINHAO
 * @Date 2022/2/13 15:11
 */
public class DefaultEcho2Service implements Echo2Service {
    @Override
    public String echo2(String message) throws NullPointerException {
        return "echo2, " + message;
    }

    @Override
    public String echo(String message) throws NullPointerException {
        return "echo, " + message;
    }
}

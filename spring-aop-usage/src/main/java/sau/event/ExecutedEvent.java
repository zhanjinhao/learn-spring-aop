package sau.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author ISJINHAO
 * @Date 2022/2/24 18:35
 */
public class ExecutedEvent extends ApplicationEvent {

    public ExecutedEvent(Object source) {
        super(source);
    }
}

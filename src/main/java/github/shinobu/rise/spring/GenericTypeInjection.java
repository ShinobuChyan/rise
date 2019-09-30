package github.shinobu.rise.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A usage sample of spring-injection with generic type
 *
 * @author Shinobu
 * @since 8/21/2019
 */
@Component
public class GenericTypeInjection {

    @Autowired
    private List<GenericTypeInterface> i;

    public void m() {

    }
}

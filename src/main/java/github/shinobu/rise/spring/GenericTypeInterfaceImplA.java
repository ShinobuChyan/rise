package github.shinobu.rise.spring;

import org.springframework.stereotype.Service;

/**
 * @author Shinobu
 * @since 8/21/2019
 */
@Service
public class GenericTypeInterfaceImplA implements GenericTypeInterface<A> {
    @Override
    public void say(A t) {
        System.out.println("a");
    }
}

package github.shinobu.rise.spring;

import org.springframework.stereotype.Service;

/**
 * @author Shinobu
 * @since 8/21/2019
 */
@Service
public class GenericTypeInterfaceImplB implements GenericTypeInterface<B> {
    @Override
    public void say(B b) {
        System.out.println("b");
    }
}

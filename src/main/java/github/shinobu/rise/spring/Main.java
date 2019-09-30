package github.shinobu.rise.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Shinobu
 * @since 8/21/2019
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public Object injection(GenericTypeInterface<A> b) {
        b.say(null);
        return new Object();
    }
}

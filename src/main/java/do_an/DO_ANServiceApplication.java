package do_an;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@Configuration
public class DO_ANServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DO_ANServiceApplication.class, args);
    }
}

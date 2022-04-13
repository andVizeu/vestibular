package br.com.vestibular;

import br.com.vestibular.data.config.BasePropertySource;
import br.com.vestibular.data.config.ConfigJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "br.com.vestibular")
@Import(ConfigJpaRepository.class)
@BasePropertySource
public class ApiApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}

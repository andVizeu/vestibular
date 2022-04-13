package br.com.vestibular.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.vestibular.data")
@EnableTransactionManagement
@EntityScan("br.com.vestibular.data")
public class ConfigJpaRepository {
}

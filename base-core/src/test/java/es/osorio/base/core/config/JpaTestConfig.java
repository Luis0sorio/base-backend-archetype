package es.osorio.base.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/*
@TestConfiguration
@EnableJpaRepositories(basePackages = "es.osorio.base.core.repository")
@EnableTransactionManagement*/
public class JpaTestConfig {
}

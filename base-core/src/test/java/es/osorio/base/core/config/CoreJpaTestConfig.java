package es.osorio.base.core.config;

import es.osorio.base.core.repository.UsuarioRepository;
import es.osorio.base.core.service.UsuarioService;
import es.osorio.base.core.service.impl.UsuarioServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@TestConfiguration
@EnableJpaRepositories(basePackages = "es.osorio.base.core.repository")
@EntityScan(basePackages = "es.osorio.base.core.domain")
@EnableTransactionManagement
@EnableJpaAuditing
public class CoreJpaTestConfig {

  @Bean
  public DataSource dataSource() {
    return DataSourceBuilder.create()
      .url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL")
      .username("sa")
      .password("")
      .driverClassName("org.h2.Driver")
      .build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(ds);
    emf.setPackagesToScan("es.osorio.base.core.domain");
    emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    Properties props = new Properties();
    props.put("hibernate.hbm2ddl.auto", "create-drop");
    props.put("hibernate.show_sql", "false");
    props.put("hibernate.format_sql", "true");
    props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    emf.setJpaProperties(props);
    return emf;
  }

  @Bean
  public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
    JpaTransactionManager tm = new JpaTransactionManager();
    tm.setEntityManagerFactory(emf.getObject());
    return tm;
  }

  @Bean
  public UsuarioService usuarioService(UsuarioRepository usuarioRepository) {
    return new UsuarioServiceImpl(usuarioRepository, /* rolRepository */ null, new BCryptPasswordEncoder());
  }
}

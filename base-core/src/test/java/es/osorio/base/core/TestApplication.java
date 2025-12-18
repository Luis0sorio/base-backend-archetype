package es.osorio.base.core;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
  "es.osorio.base.core.domain",
  "es.osorio.base.core.repository"
})
public class TestApplication {
}
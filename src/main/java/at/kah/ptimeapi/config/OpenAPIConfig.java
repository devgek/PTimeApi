package at.kah.ptimeapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PTime API")
                        .version("1.0.0")
                        .description("API für Kunden, Projekte, Buchungen und Stundensätze (Spring Data JDBC)")
                        .contact(new Contact().name("kahrer | software").email("kontakt@kahrersoftware.at"))
                        .license(new License().name("Apache 2.0")));
    }
}

package br.com.buson.b2c.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Teste Técnico Buson")
                        .version("1.0.0")
                        .description("Teste Técnico referente ao processo seletivo de 2024 com a Buson")
                        .contact(new Contact()
                                .name("Leandro Duarte Novaes")
                                .email("leandroduarte2012@hotmail.com")
                                .url("https://ldnovaes.vercel.app")));
    }
}

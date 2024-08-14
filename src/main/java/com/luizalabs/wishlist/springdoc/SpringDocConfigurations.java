package com.luizalabs.wishlist.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio Luizalabs - Backend")
                        .description("O objetivo deste projeto é desenvolver um serviço HTTP que implemente a funcionalidade de Wishlist para o cliente.\n" +
                                "- Adicionar um produto na Wishlist do cliente;\n" +
                                "- Remover um produto da Wishlist do cliente;\n" +
                                "- Consultar todos os produtos da Wishlist do cliente;\n" +
                                "- Consultar se um determinado produto está na Wishlist do\n" +
                                "cliente;")
                        .contact(new Contact()
                                .name("João Lucas Barboza")
                                .email("joaolucasbarbozaa@gmail.com")));
    }
}
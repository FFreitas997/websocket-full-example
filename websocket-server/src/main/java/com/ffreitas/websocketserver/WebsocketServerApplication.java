package com.ffreitas.websocketserver;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Websocket Server",
                version = "1.0",
                description = "Websocket Server",
                contact = @Contact(
                        name = "Francisco Freitas",
                        email = "francisco.freitas.ff@gmail.com",
                        url = "https://www.linkedin.com/in/francisco-freitas-a289b91b3"
                )
        ),
        servers = {
                @Server(
                        description = "Localhost",
                        url = "http://localhost:8088"
                )
        },
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Documentation",
                url = "https://spring.io/projects/spring-boot"
        )
)
public class WebsocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketServerApplication.class, args);
    }

}
